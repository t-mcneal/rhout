package com.rhout.backend.coordinate;

public class MidpointCalculator {

    private MidpointCalculator() {}

    public static Coordinate calculate(Coordinate A, Coordinate B) {
        double lat1 = A.getLatitude();
        double lon1 = A.getLongitude();
        double lat2 = B.getLatitude();
        double lon2 = B.getLongitude();

        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);

        double x = Math.cos(lat2) * Math.cos(dLon);
        double y = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + x) * (Math.cos(lat1) + x) + y * y));
        double lon3 = lon1 + Math.atan2(y, Math.cos(lat1) + x);

        return new Coordinate(lon3, lat3);
    }

}
