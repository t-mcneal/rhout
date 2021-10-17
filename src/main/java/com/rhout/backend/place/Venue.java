package com.rhout.backend.place;

import java.util.Objects;

public class Venue implements Place, Comparable<Venue> {
    private final String id;
    private String name;
    private String address;
    private double rating;

    public Venue(String id, String name, String address, double rating) {
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

    public int compareTo(Venue other) {
        return Double.compare(this.rating, other.getRating());
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, rating);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == getClass()) {
            return id.equals(((Venue) o).getId()) && name.equals(((Venue) o).getName());
        }
        return false;
    }
}
