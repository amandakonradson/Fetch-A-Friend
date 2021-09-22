package com.techelevator.dao;


import com.techelevator.model.PlayDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPlayDateDao implements PlayDateDao{

    @Autowired
    private JdbcLocationDao jdbcLocationDao;

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PlayDate> getAllPlayDates() {
        List<PlayDate> playDateList = new ArrayList<>();
        String sql = "SELECT play_date_id, host_pet_id, mate_pet_id, location_id, date, " +
                "start_time, duration, mate_description, mate_size, status_id FROM play_dates";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            playDateList.add(mapToRowSet(results));
        }
        return playDateList;
    }

    @Override
    public List<PlayDate> getPlayDatesByZipcode(long zipcode) {
            List<PlayDate> playDateList = new ArrayList<>();
            String sql = "SELECT play_date_id, host_pet_id, mate_pet_id, location_id, date, " +
                    "start_time, duration, mate_description, mate_size, status_id FROM play_dates " +
                    "WHERE location_id IN (SELECT location_id FROM location WHERE zipcode = ?";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, zipcode);
            while (results.next()) {
                playDateList.add(mapToRowSet(results));
            }
            return playDateList;
    }

    private PlayDate mapToRowSet(SqlRowSet results) {
        PlayDate playDate = new PlayDate();
        playDate.setPlayDateId(results.getLong("play_date_id"));
        playDate.setHostPetId(results.getLong("host_pet_id"));
        playDate.setMatePetId(results.getLong("mate_pet_id"));
        playDate.setLocationId(results.getLong("location_id"));
        playDate.setDate(results.getDate("date"));
        playDate.setStartTime(results.getTime("time"));
        playDate.setDuration(results.getLong("duration"));
        playDate.setMateDescription(results.getString("mate_description"));
        playDate.setMateSize(results.getString("mate_size"));
        playDate.setStatusId(results.getLong("status_id"));

        return playDate;
    }
}
