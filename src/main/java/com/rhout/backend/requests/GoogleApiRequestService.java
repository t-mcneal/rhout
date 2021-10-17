package com.rhout.backend.requests;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResult;
import com.rhout.backend.config.GoogleConfig;
import com.rhout.backend.coordinate.Coordinate;
import com.rhout.backend.coordinate.GoogleCoordinate;
import com.rhout.backend.requests.gmaps.GeocodingGmapsRequest;
import com.rhout.backend.requests.gmaps.GmapsRequest;
import com.rhout.backend.requests.gmaps.PlacesGmapsRequest;
import com.rhout.backend.venue.BasicVenue;
import com.rhout.backend.venue.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class GoogleApiRequestService implements RequestService<Venue> {
    private GoogleConfig googleConfig;

    @Autowired
    public GoogleApiRequestService(GoogleConfig googleConfig) {
        this.googleConfig = googleConfig;
    }

    public Coordinate getCoordinate(String address) {
        DataObject<GeocodingResult> dataObject = new DataObject<>();
        GmapsRequest<GeocodingResult> request = new GeocodingGmapsRequest(googleConfig.getContext(), address);
        GoogleApiRequest<GeocodingResult> googleApiRequest = new GoogleApiRequest<>(request, dataObject);
        googleApiRequest.execute();

        GeocodingResult[] data = googleApiRequest.getData();
        double lat = data[0].geometry.location.lat;
        double lng = data[0].geometry.location.lng;
        return new GoogleCoordinate(lat, lng);
    }

    public List<Venue> getPlaces(String searchQuery, Coordinate coordinate, int radius) {
        DataObject<PlacesSearchResult> dataObject = new DataObject<>();
        GmapsRequest<PlacesSearchResult> request = new PlacesGmapsRequest(googleConfig.getContext(),
                searchQuery, (GoogleCoordinate) coordinate, radius);
        GoogleApiRequest<PlacesSearchResult> googleApiRequest = new GoogleApiRequest<>(request, dataObject);
        googleApiRequest.execute();

        PlacesSearchResult[] data = googleApiRequest.getData();
        List<Venue> nearbyVenues = new ArrayList<>();
        for (PlacesSearchResult venue : data) {
            if (venue.businessStatus.equals("OPERATIONAL")) {
                nearbyVenues.add(new BasicVenue(venue.placeId,
                        venue.name,
                        venue.formattedAddress,
                        venue.rating));
            }
        }
        return nearbyVenues;
    }
}
