package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PlayDateDao {

    List<PlayDate> getAllPlayDates();
    List<PlayDate> getPlayDatesByZipcode(long zipcode);


}
