package com.rhout.backend.venue;

import java.util.Objects;

public class BasicVenue implements Venue, Comparable<BasicVenue> {
    private final String id;
    private String name;
    private String address;
    private double rating;

    public BasicVenue(String id, String name, String address, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public void setRating(double newRating) {
        rating = newRating;
    }

    public int compareTo(BasicVenue other) {
        return Double.compare(this.rating, other.getRating());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, rating);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == getClass()) {
            return id.equals(((BasicVenue) o).getId()) && name.equals(((BasicVenue) o).getName());
        }
        return false;
    }
}
