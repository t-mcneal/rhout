package com.rhout.backend.place;

import java.util.*;

import com.rhout.backend.coordinate.Coordinate;
import com.rhout.backend.coordinate.EarthCoordinate;
import com.rhout.backend.coordinate.MidpointCalculator;
import com.rhout.backend.requests.RequestService;

public class HalfwayVenuesResult {
    private final Coordinate coordA;
    private final Coordinate coordB;
    private final Coordinate midpoint;
    private final List<Place> nearbyVenues;

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

    private HalfwayVenuesResult(Builder builder) {
        this.coordA = builder.coordA;
        this.coordB = builder.coordB;
        this.midpoint = builder.midpoint;
        this.nearbyVenues = builder.nearbyVenues;
    }

    public static class Builder {
        private final RequestService requestService;
        private List<Place> nearbyVenues;
        private Coordinate coordA;
        private Coordinate coordB;
        private Coordinate midpoint;

        /**
         * Builder pattern for enclosing halfway venues data.
         *
         * @param requestService A {@link com.rhout.backend.requests.RequestService} to handle external API requests
         */
        public Builder(RequestService requestService) {
            this.requestService = requestService;
        }

        /**
         * Creates a {@link com.rhout.backend.coordinate.Coordinate} for each address,
         * which contains longitude and latitude values. Then, the
         * {@link com.rhout.backend.coordinate.MidpointCalculator} calculates a midpoint
         * coordinate to determine a halfway location between these two addresses.
         *
         * @param address1 A postal address (i.e. house number, street name, city, state and zipcode).
         * @param address2 A postal address (i.e. house number, street name, city, state and zipcode).
         * @return Returns this {@code HalfwayVenuesBuilder} for call chaining.
         */
        public Builder buildCoordinates(String address1, String address2) {
            String[] addresses = {address1, address2};
            Coordinate[] coordinates = new Coordinate[2];
            for (int i = 0; i < addresses.length; i++) {
                coordinates[i] = requestService.getCoordinate(addresses[i]);
            }
            this.coordA = coordinates[0];
            this.coordB = coordinates[1];
            this.midpoint = requestService.calculateMidpoint(this.coordA, this.coordB);
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
                throw new IllegalStateException("Must calculate midpoint before finding nearby venues");
            }
            this.nearbyVenues = requestService.getPlaces(searchQuery, midpoint, 1600);
            return this;
        }

        /**
         * Converts this builder into a {@code HalfwayVenuesResult}.
         *
         * @return Returns the built {@code HalfwayVenuesResult}.
         */
        public HalfwayVenuesResult build() {
            if (this.midpoint == null || this.nearbyVenues.size() == 0)
                throw new IllegalStateException("The HalfwayVenueResult object was not built properly");
            return new HalfwayVenuesResult(this);
        }
    }
}
