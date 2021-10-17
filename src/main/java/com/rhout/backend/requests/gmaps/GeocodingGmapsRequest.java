package com.rhout.backend.requests.gmaps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;


public class GeocodingGmapsRequest implements GmapsRequest<GeocodingResult> {
    private GeoApiContext context;
    private String address;

    public GeocodingGmapsRequest(GeoApiContext context, String address) {
        this.context = context;
        this.address = address;
    }

    public GeocodingResult[] execute() {
        try {
            GeocodingResult[] geocodingResult = GeocodingApi.geocode(context, address).await();
            return geocodingResult;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
