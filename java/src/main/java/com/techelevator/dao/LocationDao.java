package com.techelevator.dao;

import com.techelevator.model.Location;

import java.util.List;

public interface LocationDao {

    List<Location> getLocationByZipCode(int zipcode);

    void addLocation(String description, int zipcode);
}
