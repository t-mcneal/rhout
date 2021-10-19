package com.rhout.backend.place;

import com.rhout.backend.requests.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class HalfwayVenuesResultService {
    @Autowired
    @Qualifier("googleApiRequestService")
    private final RequestService requestService;

    public HalfwayVenuesResultService(RequestService requestService) {
        this.requestService = requestService;
    }

    public List<Place> getHalfwayVenues(String address1, String address2) {
        HalfwayVenuesResult halfwayVenues = new HalfwayVenuesResult.Builder(requestService)
                .buildCoordinates(address1, address2)
                .findNearbyVenues("music venues", 1600)
                .build();
        return halfwayVenues.getVenues();
    }

    public List<Place> getTopRatedVenues(String address1, String address2, int amount) {
        HalfwayVenuesResult halfwayVenues = new HalfwayVenuesResult.Builder(requestService)
                .buildCoordinates(address1, address2)
                .findNearbyVenues("music venues", 1600)
                .build();
        return getTopRated(halfwayVenues.getVenues(), amount);
    }

    private List<Place> getTopRated(List<Place> nearbyVenues, int amount) {
        if (nearbyVenues.size() == 0) {
            throw new IllegalStateException("NearbyVenues is an empty list");
        } else if (amount <= 0) {
            throw new IllegalArgumentException("Top rated amount must be greater than 0");
        } else if (nearbyVenues.size() < amount) {
            throw new IllegalArgumentException("Number of nearby venues is less than top rated amount");
        } else {
            ArrayList<Place> topRatedVenues = new ArrayList<>();
            PriorityQueue<Venue> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            for (Place venue : nearbyVenues) {
                maxHeap.add((Venue) venue);
            }
            for (int i = 0; i < amount; i++) {
                topRatedVenues.add(maxHeap.remove());
            }
            return topRatedVenues;
        }
    }
}
