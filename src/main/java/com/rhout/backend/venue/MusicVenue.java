package com.rhout.backend.venue;


public class MusicVenue extends BasicVenue {
    private final String type;

    public MusicVenue(String id, String name, String address, double rating) {
        super(id, name, address, rating);
        type = "music";
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
       if (o instanceof MusicVenue) {
           return super.equals(o);
       }
       return false;
    }
}
