package com.rhout.backend.requests;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResult;
import com.rhout.backend.requests.gmaps.GmapsRequest;
import com.rhout.backend.requests.gmaps.GeocodingGmapsRequest;
import com.rhout.backend.requests.gmaps.PlacesGmapsRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class GoogleApiRequestTest {

    @Test
    @DisplayName("Test the execution of a Geocoding request, which should store data inside a DataObject")
    void testGeocodingRequestExecute() {
        GeocodingResult[] mockGeocodingResult = {mock(GeocodingResult.class), mock(GeocodingResult.class)};
        GmapsRequest<GeocodingResult> mockGmapsRequest = mock(GeocodingGmapsRequest.class);
        when(mockGmapsRequest.execute()).thenReturn(mockGeocodingResult);
        DataObject<GeocodingResult> dataObject = new DataObject<>();

        GoogleApiRequest<GeocodingResult> request = new GoogleApiRequest<>(mockGmapsRequest, dataObject);
        request.execute();
        GeocodingResult[] data = request.getData();

        assertNotNull(data);
    }

    @Test
    @DisplayName("Test the execution of a Places Search request, which should store data inside a DataObject")
    void testPlacesRequestExecute() {
        PlacesSearchResult[] mockPlacesResult = {mock(PlacesSearchResult.class), mock(PlacesSearchResult.class)};
        GmapsRequest<PlacesSearchResult> mockGmapsRequest = mock(PlacesGmapsRequest.class);
        when(mockGmapsRequest.execute()).thenReturn(mockPlacesResult);
        DataObject<PlacesSearchResult> dataObject = new DataObject<>();

        GoogleApiRequest<PlacesSearchResult> request = new GoogleApiRequest<>(mockGmapsRequest, dataObject);
        request.execute();
        PlacesSearchResult[] data = request.getData();

        assertNotNull(data);
    }
}