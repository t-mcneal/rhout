package com.rhout.backend.venue;

import com.rhout.backend.config.GoogleConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class HalfwayVenuesResultController {
    private final GoogleConfig googleConfig;

    @Autowired
    public HalfwayVenuesResultController(GoogleConfig googleConfig) {
        this.googleConfig = googleConfig;
    }

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @PostMapping("/api/v1/venues/halfway/top-rated")
    public List<Venue> getTopRatedHalfwayVenues(@RequestParam String address1, @RequestParam String address2) {
        HalfwayVenuesResult venues = new HalfwayVenuesResult.Builder(googleConfig.getContext())
                .buildCoordinates(address1, address2)
                .findNearbyVenues("music venue")
                .topRated(5)
                .build();

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson.toJson(venues.getTopRated()));

        return venues.getTopRated();
    }
}
