package com.rhout.backend.coordinate;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoogleCoordinateTest {
    Coordinate coordinate = coordinate = new GoogleCoordinate(1.5, 2.5);

    @Test
    @DisplayName("Test getting latitude of a coordinate")
    void testGetLatitude() {
        assertEquals(1.5, coordinate.getLatitude());
    }

    @Test
    @DisplayName("Test getting longitude of a coordinate")
    void testGetLongitude() {
        assertEquals(2.5, coordinate.getLongitude());
    }

    @Test
    @DisplayName("Test equality of two coordinates when true")
    void testEqualsWhenTrue() {
        Coordinate coordinate2 = new GoogleCoordinate(1.5, 2.5);
        assertTrue(coordinate2.equals(coordinate));
    }

    @Test
    @DisplayName("Test equality of two coordinate when false")
    void testEqualWhenFalse() {
        Coordinate coordinate2 = new GoogleCoordinate(1.5, 4.8);
        assertFalse(coordinate2.equals(coordinate));
    }
}