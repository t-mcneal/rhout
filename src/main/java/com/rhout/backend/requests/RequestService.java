package com.rhout.backend.requests;

import com.rhout.backend.coordinate.Coordinate;
import com.rhout.backend.place.Place;

import java.util.List;

public interface RequestService {

    public Coordinate getCoordinate(String address);

    public List<Place> getPlaces(String searchQuery, Coordinate coordinate, int radius);
}
