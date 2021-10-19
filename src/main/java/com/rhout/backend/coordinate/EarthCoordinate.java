package com.rhout.backend.coordinate;

public class EarthCoordinate implements Coordinate{
    private final double lat;
    private final double lng;

    public EarthCoordinate(double lat, double lng) {
       this.lat = lat;
       this.lng = lng;
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
