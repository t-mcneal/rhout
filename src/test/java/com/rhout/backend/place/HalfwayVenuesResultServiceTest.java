package com.rhout.backend.place;

import com.rhout.backend.coordinate.Coordinate;
import com.rhout.backend.requests.GoogleApiRequestService;
import com.rhout.backend.requests.RequestService;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HalfwayVenuesResultServiceTest {
    static List<Place> places;

    @BeforeAll
    static void setup() {
        places = new ArrayList<Place>();
        places.add(new Venue("1", "Music Hall", "123 Mock Dr", 4.4)); // top rated - 4
        places.add(new Venue("2", "Epic Sounds", "456 Mock Dr", 5.0)); // top rated - 1
        places.add(new Venue("3", "Sound Place", "789 Mock Dr.", 4.8)); // top rated - 3
        places.add(new Venue("4", "New Sound", "912 Mock Dr.", 4.0));
        places.add(new Venue("5", "Great Hall", "345 Mock Dr.", 4.2)); // top rated - 5
        places.add(new Venue("6", "Blue Muse", "678 Mock Dr.", 4.1));
        places.add(new Venue("7", "Indigo", "901 Mock Dr.", 3.2));
        places.add(new Venue("8", "Blues House", "789 Mock Dr.", 3.7));
        places.add(new Venue("9", "Vibe", "234 Mock Dr.", 4.9)); // top rated - 2
        places.add(new Venue("10", "The Venue", "567 Mock Dr.", 3.0));
    }

    @Test
    @Order(1)
    @DisplayName("Test getting halfway venues via the builder class")
    void testGetHalfwayVenues() {
        RequestService mockRequestService = mock(GoogleApiRequestService.class);
        when(mockRequestService.getCoordinate(any(String.class))).thenReturn(mock(Coordinate.class));
        when(mockRequestService.calculateMidpoint(any(Coordinate.class), any(Coordinate.class)))
                .thenReturn(mock(Coordinate.class));
        when(mockRequestService.getPlaces(any(String.class), any(Coordinate.class), any(int.class)))
                .thenReturn(places);
        HalfwayVenuesResultService service = new HalfwayVenuesResultService(mockRequestService);

        List<Place> halfwayVenues = service.getHalfwayVenues("111 Fake Address", "222 Fake Address");
        assertEquals(places, halfwayVenues);
    }

    @Test
    @Order(2)
    @DisplayName("Test finding the top rated venues")
    void testTopRated() {
        HalfwayVenuesResultService service = new HalfwayVenuesResultService(mock(RequestService.class));

        List<Place> topRatedVenues = service.topRated(places, 5);
        assertEquals(5, topRatedVenues.toArray().length);
        assertEquals("Epic Sounds", topRatedVenues.get(0).getName());
        assertEquals("Great Hall", topRatedVenues.get(4).getName());
    }

    @Test
    @Order(3)
    @DisplayName("Test topRated method when list of places size is less than amount")
    void testTopRated_2() {
        places = places.subList(0, 3);
        HalfwayVenuesResultService service = new HalfwayVenuesResultService(mock(RequestService.class));

        assertThrows(IllegalArgumentException.class, () -> service.topRated(places, 5));
    }

    @Test
    @Order(4)
    @DisplayName("Test topRated method when amount is <= 0")
    void testTopRated_3() {
        places = places.subList(0, 3);
        HalfwayVenuesResultService service = new HalfwayVenuesResultService(mock(RequestService.class));

        assertThrows(IllegalArgumentException.class, () -> service.topRated(places, -1));
    }

    @Test
    @Order(5)
    @DisplayName("Test topRated method when list of places is empty")
    void testTopRated_4() {
        places = new ArrayList<>();
        HalfwayVenuesResultService service = new HalfwayVenuesResultService(mock(RequestService.class));

        assertThrows(IllegalArgumentException.class, () -> service.topRated(places, -1));
    }
}