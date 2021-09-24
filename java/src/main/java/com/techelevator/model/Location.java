package com.techelevator.model;

public class Location {

    private long locationId;
    private String description;
    private int zipcode;

    public Location(){

    }

    public Location(String description, int zipcode) {
        this.description = description;
        this.zipcode = zipcode;
    }

    public Location(long locationId, String description, int zipcode) {
        this.locationId = locationId;
        this.description = description;
        this.zipcode = zipcode;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", description='" + description + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }
}
