package com.rhout.backend.place;


public interface Place {

    String getId();

    String getName();

    String getAddress();

    double getRating();

    void setName(String newName);

    void setAddress(String newAddress);

    void setRating(double newRating);
}
