package com.rhout.backend.coordinate;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MidpointCalculatorTest {

    @Test
    void testCalculate() {
        Coordinate coordinate1 = new GoogleCoordinate(5, 4);
        Coordinate coordinate2 = new GoogleCoordinate(1, 4);
        Map<String, Double> midpoint = MidpointCalculator.calculate(coordinate1, coordinate2);

        assertEquals(3, midpoint.get("latitude"));
        assertEquals(4, midpoint.get("longitude"));
    }
}