package com.rhout.backend.place;

import com.rhout.backend.config.GoogleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HalfwayVenuesResultService {
    private final GoogleConfig googleConfig;

    @Autowired
    public HalfwayVenuesResultService(GoogleConfig googleConfig) {
        this.googleConfig = googleConfig;
    }

    public HalfwayVenuesResult getHalfwayVenues(String address1, String address2) {
        return new HalfwayVenuesResult.Builder(googleConfig.getContext())
                .buildCoordinates(address1, address2)
                .findNearbyVenues("music venues")
                .topRated(5)
                .build();
    }
}
