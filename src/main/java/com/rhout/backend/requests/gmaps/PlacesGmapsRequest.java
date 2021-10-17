package com.rhout.backend.requests.gmaps;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResult;
import com.rhout.backend.coordinate.Coordinate;
import com.rhout.backend.coordinate.GoogleCoordinate;


public class PlacesGmapsRequest implements GmapsRequest<PlacesSearchResult> {
    private GeoApiContext context;
    private String searchQuery;
    private Coordinate coordinate;
    private int radius;

    public PlacesGmapsRequest(GeoApiContext context, String searchQuery,
                              Coordinate coordinate, int radius) {
        this.context = context;
        this.searchQuery = searchQuery;
        this.coordinate = coordinate;
        this.radius = radius;
    }

    public PlacesSearchResult[] execute() {
        try {
            PlacesSearchResult[] placesResult = PlacesApi.textSearchQuery(context,
                    searchQuery, (GoogleCoordinate) coordinate)
                    .radius(radius)
                    .await()
                    .results;
            return placesResult;
        } catch(Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
