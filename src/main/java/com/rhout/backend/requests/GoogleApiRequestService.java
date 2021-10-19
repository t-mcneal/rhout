package com.rhout.backend.requests;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResult;
import com.rhout.backend.config.GoogleConfig;
import com.rhout.backend.coordinate.Coordinate;
import com.rhout.backend.coordinate.EarthCoordinate;
import com.rhout.backend.coordinate.GoogleCoordinate;
import com.rhout.backend.coordinate.MidpointCalculator;
import com.rhout.backend.requests.gmaps.GeocodingGmapsRequest;
import com.rhout.backend.requests.gmaps.GmapsRequest;
import com.rhout.backend.requests.gmaps.PlacesGmapsRequest;
import com.rhout.backend.place.Venue;
import com.rhout.backend.place.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("googleApiRequestService")
public class GoogleApiRequestService implements RequestService {
    private final GoogleConfig googleConfig;
    protected GoogleApiRequest<GeocodingResult> geocodingGoogleApiRequest;
    protected GoogleApiRequest<PlacesSearchResult> placesGoogleApiRequest;

    @Autowired
    public GoogleApiRequestService(GoogleConfig googleConfig,
                                   GoogleApiRequest<GeocodingResult> geocodingGoogleApiRequest,
                                   GoogleApiRequest<PlacesSearchResult> placesGoogleApiRequest) {
        this.googleConfig = googleConfig;
        this.geocodingGoogleApiRequest = geocodingGoogleApiRequest;
        this.placesGoogleApiRequest = placesGoogleApiRequest;
    }

    public Coordinate getCoordinate(String address) {
        DataObject<GeocodingResult> dataObject = new DataObject<>();
        GmapsRequest<GeocodingResult> request = new GeocodingGmapsRequest(googleConfig.getContext(), address);
        geocodingGoogleApiRequest.setGmapsRequest(request);
        geocodingGoogleApiRequest.setDataObject(dataObject);
        geocodingGoogleApiRequest.execute();

        GeocodingResult[] data = geocodingGoogleApiRequest.getData();
        double lat = data[0].geometry.location.lat;
        double lng = data[0].geometry.location.lng;
        return new GoogleCoordinate(lat, lng);
    }

    public List<Place> getPlaces(String searchQuery, Coordinate coordinate, int radius) {
        DataObject<PlacesSearchResult> dataObject = new DataObject<>();
        GmapsRequest<PlacesSearchResult> request = new PlacesGmapsRequest(googleConfig.getContext(),
                searchQuery, coordinate, radius);
        placesGoogleApiRequest.setGmapsRequest(request);
        placesGoogleApiRequest.setDataObject(dataObject);
        placesGoogleApiRequest.execute();

        PlacesSearchResult[] data = placesGoogleApiRequest.getData();
        List<Place> nearbyPlaces = new ArrayList<>();
        for (PlacesSearchResult venue : data) {
            if (venue.businessStatus.equals("OPERATIONAL")) {
                nearbyPlaces.add(new Venue(venue.placeId,
                        venue.name,
                        venue.formattedAddress,
                        venue.rating));
            }
        }
        return nearbyPlaces;
    }

    public Coordinate calculateMidpoint(Coordinate A, Coordinate B) {
        Coordinate midpoint = MidpointCalculator.calculate(A, B);
        return new GoogleCoordinate(midpoint.getLatitude(), midpoint.getLongitude());
    }
}
