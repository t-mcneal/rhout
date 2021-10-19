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
    public static EarthCoordinate calculate(Coordinate A, Coordinate B) {
        double lat = (A.getLatitude() + B.getLatitude()) / 2;
        double lng = (A.getLongitude() + B.getLongitude()) / 2;
        return new EarthCoordinate(lat, lng);
    }
}
