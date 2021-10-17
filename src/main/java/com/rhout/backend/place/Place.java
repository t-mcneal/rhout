package com.rhout.backend.place;


public interface Place {

    public String getId();

    public String getName();

    public String getAddress();

    public double getRating();

    public void setName(String newName);

    public void setAddress(String newAddress);

    public void setRating(double newRating);

}
