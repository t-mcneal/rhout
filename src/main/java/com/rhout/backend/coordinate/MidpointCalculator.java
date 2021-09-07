package com.rhout.backend.coordinate;

public class MidpointCalculator {

    private MidpointCalculator() {}

    /**
     * @param A A coordinate containing latitude/longitude
     * @param B A coordinate containing latitude/longitude
     * @return A midpoint between two coordinates
     */
    public static Coordinate calculate(Coordinate A, Coordinate B) {
        double lat3 = (A.getLatitude() + B.getLatitude()) / 2;
        double lng3 = (A.getLongitude() + B.getLongitude()) / 2;
        return new Coordinate(lat3, lng3);
    }
}
