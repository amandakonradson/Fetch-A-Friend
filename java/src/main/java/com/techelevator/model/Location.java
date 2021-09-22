package com.techelevator.model;

public class Location {

    private long locationId;
    private String description;
    private long zipcode;

    public Location(){

    }

    public Location(long locationId, String description, long zipcode) {
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

    public long getZipcode() {
        return zipcode;
    }

    public void setZipcode(long zipcode) {
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
