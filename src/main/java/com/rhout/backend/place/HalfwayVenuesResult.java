package com.rhout.backend.place;

import java.util.*;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResult;

import com.rhout.backend.coordinate.Coordinate;
import com.rhout.backend.coordinate.GoogleCoordinate;
import com.rhout.backend.coordinate.MidpointCalculator;

public class HalfwayVenuesResult {
    private final Coordinate coordA;
    private final Coordinate coordB;
    private final Coordinate midpoint;
    private final List<Place> nearbyVenues;
    private final List<Place> topRatedVenues;

    /**
     * @return A {@link com.rhout.backend.coordinate.Coordinate} for the first address
     * parameter used to during the build process.
     */
    public Coordinate getCoordinateA() { return coordA; }

    /**
     * @return A {@link com.rhout.backend.coordinate.Coordinate} for the second address
     * parameter used to during the build process.
     */
    public Coordinate getCoordinateB() { return coordB; }

    /**
     * @return A {@link com.rhout.backend.coordinate.Coordinate} for the halfway location.
     */
    public Coordinate getMidpoint() { return midpoint; }

    /**
     * @return A {@link java.util.List} of {@link Place} objects
     * that contain data for places that are halfway between two locations.
     */
    public List<Place> getVenues() { return nearbyVenues; }

    /**
     * @return Number of total venues contained in this {@code HalfwayVenuesResult}
     */
    public int getNumOfVenues() { return nearbyVenues.size(); }

    /**
     * @return A {@link java.util.List} containing top rated {@link Place} objects.
     */
    public List<Place> getTopRated() { return topRatedVenues; }

    private HalfwayVenuesResult(Builder builder) {
        this.coordA = builder.coordA;
        this.coordB = builder.coordB;
        this.midpoint = builder.midpoint;
        this.nearbyVenues = builder.nearbyVenues;
        this.topRatedVenues = builder.topRatedVenues;
    }

    public static class Builder {
        private final List<Place> nearbyVenues = new ArrayList<>();
        private final List<Place> topRatedVenues = new ArrayList<>();
        private final GeoApiContext context;
        private Coordinate coordA;
        private Coordinate coordB;
        private Coordinate midpoint;

        /**
         * Builder pattern for enclosing halfway venues data.
         *
         * @param context A {@link com.google.maps.GeoApiContext} object
         */
        public Builder(GeoApiContext context) {
            this.context = context;
        }

        /**
         * Creates a {@link com.rhout.backend.coordinate.Coordinate} for each address,
         * which stores longitude and latitude values queried using
         * Google Maps {@link com.google.maps.GeocodingApi}.
         * Then, the {@link com.rhout.backend.coordinate.MidpointCalculator} calculates a midpoint
         * coordinate to determine a halfway location between these two addresses.
         *
         * @param address1 A postal address (i.e. house number, street name, city, state and zipcode).
         * @param address2 A postal address (i.e. house number, street name, city, state and zipcode).
         * @return Returns this {@code HalfwayVenuesBuilder} for call chaining.
         */
        public Builder buildCoordinates(String address1, String address2) {
            String[] addresses = {address1, address2};
            Coordinate[] coordinates = new GoogleCoordinate[2];
            double lat;
            double lng;
            try {
                for (int i = 0; i < addresses.length; i++) {
                    GeocodingResult[] results = GeocodingApi.geocode(this.context, addresses[i]).await();
                    lat = results[0].geometry.location.lat;
                    lng = results[0].geometry.location.lng;
                    coordinates[i] = new GoogleCoordinate(lat, lng);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
            this.coordA = coordinates[0];
            this.coordB = coordinates[1];
            Map<String, Double> midpoint = MidpointCalculator.calculate(this.coordA, this.coordB);
            this.midpoint = new GoogleCoordinate(midpoint.get("latitude"), midpoint.get("longitude"));
            return this;
        }

        /**
         * Specifies a query of text to search using Google Maps {@link com.google.maps.PlacesApi}.
         * The search result is a {@link java.util.List} of JSON objects containing data for query related
         * places, with a bias radius of 1600 meters (approximately 1 mile) of the midpoint coordinate. The JSON
         * data is parsed and stored as {@link Place} objects in a new {@code List}.
         *
         * @param searchQuery Text describing a type of place to search.
         * @return Returns this {@code HalfwayVenuesBuilder} for call chaining.
         */
        public Builder findNearbyVenues(String searchQuery) {
            if (this.midpoint == null) {
                throw new IllegalStateException("Must calculate midpoint before finding nearby venues.");
            }
            try {
                // search venues near midpoint coordinate
                PlacesSearchResult[] placesResults = PlacesApi.textSearchQuery(this.context,
                        searchQuery, (GoogleCoordinate) this.midpoint)
                        .radius(1600)
                        .await()
                        .results;

                // create list of place objects based on search JSON results
                for (PlacesSearchResult venue : placesResults) {
                    if (venue.businessStatus.equals("OPERATIONAL")) {
                        this.nearbyVenues.add(new Venue(venue.placeId,
                                venue.name,
                                venue.formattedAddress,
                                venue.rating));
                    }
                }
            } catch(Exception e) {
                throw new IllegalArgumentException(e);
            }
            return this;
        }

        /**
         * Finds this amount of top rated venues among the halfway venues.
         *
         * @param amount The amount of top rated venues to find.
         * @return Returns this {@code HalfwayVenuesBuilder} for call chaining.
         */
        public Builder topRated(int amount) {
            if (this.nearbyVenues.size() == 0) {
                throw new IllegalStateException("Must find nearby venues before building top rated venues.");
            } else if (amount <= 0) {
                throw new IllegalArgumentException("Top rated amount must be greater than 0.");
            } else if (this.nearbyVenues.size() < amount) {
                throw new IllegalArgumentException("Number of total halfway venues is " +
                        this.nearbyVenues.size() + ", which is less than a top rated amount of " + amount);
            }
            PriorityQueue<Venue> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            for (Place venue : this.nearbyVenues) {
                maxHeap.add((Venue) venue);
            }
            for (int i = 0; i < amount; i++) {
                this.topRatedVenues.add(maxHeap.remove());
            }
            return this;
        }

        /**
         * Converts this builder into a {@code HalfwayVenuesResult}.
         *
         * @return Returns the built {@code HalfwayVenuesResult}.
         */
        public HalfwayVenuesResult build() {
            if (this.midpoint == null || this.nearbyVenues.size() == 0 || this.topRatedVenues.size() == 0)
                throw new IllegalStateException("The HalfwayVenueResult object was not built properly." +
                        " Make sure all HalfwayVenuesBuilder methods were called during method chaining.");
            return new HalfwayVenuesResult(this);
        }
    }
}