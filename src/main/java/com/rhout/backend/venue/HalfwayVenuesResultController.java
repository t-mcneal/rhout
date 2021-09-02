package com.rhout.backend.venue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HalfwayVenuesResultController {
    private HalfwayVenuesResultService halfwayVenuesResultService;

    @Autowired
    public HalfwayVenuesResultController(HalfwayVenuesResultService halfwayVenuesResultService) {
        this.halfwayVenuesResultService = halfwayVenuesResultService;
    }

    @PostMapping("/api/v1/venues/halfway/top-rated")
    public List<Venue> findTopRatedVenues(@RequestBody Map<String, String> json) {
        String address1 = json.get("address1");
        String address2 = json.get("address2");
        HalfwayVenuesResult venues = halfwayVenuesResultService.getHalfwayVenues(address1, address2);
        return venues.getTopRated();
    }
}
