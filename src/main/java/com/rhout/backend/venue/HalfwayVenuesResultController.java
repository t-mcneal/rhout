package com.rhout.backend.venue;

import com.rhout.backend.config.GoogleConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    public List<Venue> getTopRatedHalfwayVenues(@RequestBody Map<String, String> json) {
        String address1 = json.get("address1");
        String address2 = json.get("address2");
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
