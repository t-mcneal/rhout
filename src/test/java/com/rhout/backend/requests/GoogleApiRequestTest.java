package com.rhout.backend.requests;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResult;

import com.rhout.backend.requests.gmaps.GmapsRequest;
import com.rhout.backend.requests.gmaps.GeocodingGmapsRequest;
import com.rhout.backend.requests.gmaps.PlacesGmapsRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class GoogleApiRequestTest {

    @Test
    @DisplayName("Test the execution of a Geocoding request")
    void testGeocodingRequestExecute() {
        GeocodingResult[] mockGeocodingResult = {mock(GeocodingResult.class), mock(GeocodingResult.class)};
        GmapsRequest<GeocodingResult> mockRequest = mock(GeocodingGmapsRequest.class);
        when(mockRequest.execute()).thenReturn(mockGeocodingResult);
        DataObject<GeocodingResult> dataObject = new DataObject<>();

        GoogleApiRequest<GeocodingResult> googleApiRequest = new GoogleApiRequest<>(mockRequest, dataObject);
        googleApiRequest.execute();
        GeocodingResult[] data = googleApiRequest.getData();

        assertNotNull(data);
        verify(mockRequest).execute();
    }

    @Test
    @DisplayName("Test the execution of a Places Search request")
    void testPlacesRequestExecute() {
        PlacesSearchResult[] mockPlacesResult = {mock(PlacesSearchResult.class), mock(PlacesSearchResult.class)};
        GmapsRequest<PlacesSearchResult> mockRequest = mock(PlacesGmapsRequest.class);
        when(mockRequest.execute()).thenReturn(mockPlacesResult);
        DataObject<PlacesSearchResult> dataObject = new DataObject<>();

        GoogleApiRequest<PlacesSearchResult> googleApiRequest = new GoogleApiRequest<>(mockRequest, dataObject);
        googleApiRequest.execute();
        PlacesSearchResult[] data = googleApiRequest.getData();

        assertNotNull(data);
        verify(mockRequest).execute();
    }
}