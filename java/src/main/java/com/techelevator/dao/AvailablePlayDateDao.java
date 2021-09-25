package com.techelevator.dao;

import com.techelevator.model.AvailablePlayDate;

import java.util.List;

public interface AvailablePlayDateDao {

    List<AvailablePlayDate> getAvailablePlayDates();

    void updatePlaydate(long playDateId);

}
