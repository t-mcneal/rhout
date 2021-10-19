package com.rhout.backend.requests;

import com.google.gson.Gson;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResult;

import com.rhout.backend.config.GoogleConfig;
import com.rhout.backend.coordinate.Coordinate;
import com.rhout.backend.coordinate.GoogleCoordinate;
import com.rhout.backend.place.Place;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoogleApiRequestServiceTest {
    static GoogleApiRequestService googleApiRequestService;
    static GoogleApiRequest<GeocodingResult> mockGeocodingGoogleApiRequest;
    static GoogleApiRequest<PlacesSearchResult> mockPlacesGoogleApiRequest;


    @BeforeAll
    @SuppressWarnings("unchecked")
    static void setup() {

        // Mock the Google Config
        GoogleConfig mockGoogleConfig = mock(GoogleConfig.class);
        Gson gson = new Gson();

        // Mock the Geocode API Request
        String geocodeJSON = "{'geometry': {'location': {'lat':1, 'lng':2}}}";
        GeocodingResult[] mockGeocodingResult = {gson.fromJson(geocodeJSON, GeocodingResult.class)};
        mockGeocodingGoogleApiRequest = mock(GoogleApiRequest.class);
        when(mockGeocodingGoogleApiRequest.getData()).thenReturn(mockGeocodingResult);

        // Mock the Places API Request
        String places1JSON = "{'placeID': 1, 'name': 'Music Hall One'," +
                "formattedAddress: '111 Picket St.', 'rating': 4.5, 'businessStatus': 'OPERATIONAL'}";
        String places2JSON = "{'placeID': 1, 'name': 'Music Hall Two'," +
                "formattedAddress: '222 Picket St.', 'rating': 4.2, 'businessStatus': 'OPERATIONAL'}";
        PlacesSearchResult[] mockPlacesResult = {gson.fromJson(places1JSON, PlacesSearchResult.class),
                gson.fromJson(places2JSON, PlacesSearchResult.class)};
        mockPlacesGoogleApiRequest = mock(GoogleApiRequest.class);
        when(mockPlacesGoogleApiRequest.getData()).thenReturn(mockPlacesResult);

        // Create a GoogleApiRequestService object
        googleApiRequestService = new GoogleApiRequestService(mockGoogleConfig,
                mockGeocodingGoogleApiRequest, mockPlacesGoogleApiRequest);
    }

    @Test
    @DisplayName("Test getting a coordinate using Google Maps Geocoding API")
    void testGetCoordinate() {
        Coordinate coordinate = googleApiRequestService.getCoordinate("111 Private Dr, Los Angeles, CA, 11111");

        assertEquals(2, coordinate.getLongitude());
        assertEquals(1, coordinate.getLatitude());
        verify(mockGeocodingGoogleApiRequest).execute();
    }

    @Test
    @DisplayName("Test getting places using Google Maps Places API")
    void testGetPlaces() {
        Coordinate midpoint = new GoogleCoordinate(1, 2);
        List<Place> placeList = googleApiRequestService.getPlaces("music venues", midpoint, 1600);

        assertEquals("Music Hall One", placeList.get(0).getName());
        assertEquals("Music Hall Two", placeList.get(1).getName());
        verify(mockPlacesGoogleApiRequest).execute();
    }
}