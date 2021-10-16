package com.rhout.backend.coordinate;

import com.google.maps.model.LatLng;

public class GoogleCoordinate extends LatLng implements Coordinate {

    public GoogleCoordinate(double lat, double lng) {
        super(lat, lng);
    }

    public double getLatitude() { return lat; }

    public double getLongitude() { return lng; }

    @Override
    public String toString() {
        return "Coordinate{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return (int) (lat * lng);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == getClass()) {
            return lat == ((Coordinate) o).getLatitude() && lng == ((Coordinate) o).getLongitude();
        }
        return false;
    }
}
