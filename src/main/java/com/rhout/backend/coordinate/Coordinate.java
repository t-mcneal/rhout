package com.rhout.backend.coordinate;

import com.google.maps.model.LatLng;

public class Coordinate extends LatLng {

    public Coordinate(double lon, double lat) {
        super(lat, lon);
    }

    public double getLatitude() { return lat; }

    public double getLongitude() {
        return lng;
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
