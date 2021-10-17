package com.rhout.backend.requests;

import com.rhout.backend.coordinate.Coordinate;
import java.util.List;

public interface RequestService<T> {

    public Coordinate getCoordinate(String address);

    public List<T> getPlaces(String searchQuery, Coordinate coordinate, int radius);
}
