package com.techelevator.dao;

import com.techelevator.model.AvailablePlayDate;
import com.techelevator.model.PlayDate;

import java.util.List;

public interface AvailablePlayDateDao {

    List<AvailablePlayDate> getAvailablePlayDates();
    AvailablePlayDate getAvailablePlayDateByPlayDateId(long playDateId);
    void updatePlaydate(long playDateId);

}
