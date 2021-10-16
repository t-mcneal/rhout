package com.rhout.backend.coordinate;

import java.util.Map;
import java.util.HashMap;

public class MidpointCalculator {

    private MidpointCalculator() {}

    /**
     * @param A A coordinate containing latitude/longitude
     * @param B A coordinate containing latitude/longitude
     * @return A midpoint between two coordinates
     */
    public static Map<String, Double> calculate(Coordinate A, Coordinate B) {
        Map<String, Double> midpointCoordinate = new HashMap<>();
        double lat = (A.getLatitude() + B.getLatitude()) / 2;
        double lng = (A.getLongitude() + B.getLongitude()) / 2;
        midpointCoordinate.put("latitude", lat);
        midpointCoordinate.put("longitude", lng);
        return midpointCoordinate;
    }
}
