package com.rhout.backend.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MidpointCalculatorTest {

    @Test
    @DisplayName("Test calculating the midpoint of two coordinates")
    void testCalculate() {
        Coordinate coordinate1 = new GoogleCoordinate(5, 4);
        Coordinate coordinate2 = new GoogleCoordinate(1, 4);
        Coordinate midpoint = MidpointCalculator.calculate(coordinate1, coordinate2);

        assertEquals(3, midpoint.getLatitude());
        assertEquals(4, midpoint.getLongitude());
    }
}