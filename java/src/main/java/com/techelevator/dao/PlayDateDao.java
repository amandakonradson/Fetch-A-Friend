package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PlayDateDao {

    List<PlayDate> getAllPlayDates();
    List<PlayDate> getPlayDatesByZipcode(long zipcode);
    List<PlayDate> getPlayDatesByUserId(long userId);
    void createPlayDate(PlayDate playDate);
    LocalDate getCurrentDate();
    void updatePlayDate(long playDateId, long matePetId);




}
