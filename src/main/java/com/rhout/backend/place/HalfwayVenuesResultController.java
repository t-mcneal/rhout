package com.rhout.backend.place;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HalfwayVenuesResultController {
    private HalfwayVenuesResultService halfwayVenuesResultService;

    @Autowired
    public HalfwayVenuesResultController(HalfwayVenuesResultService halfwayVenuesResultService) {
        this.halfwayVenuesResultService = halfwayVenuesResultService;
    }

    @PostMapping("/api/v1/venues/halfway")
    public List<Place> findHalfwayVenues(@RequestBody Map<String, String> json) {
        String address1 = json.get("address1");
        String address2 = json.get("address2");
        return halfwayVenuesResultService.getHalfwayVenues(address1, address2);
    }

    @PostMapping("/api/v1/venues/halfway/top-rated")
    public List<Place> findTopRatedVenues(@RequestBody Map<String, String> json) {
        String address1 = json.get("address1");
        String address2 = json.get("address2");
        return halfwayVenuesResultService.getTopRatedVenues(address1, address2, 5);
    }
}
