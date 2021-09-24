package com.techelevator.dao;


import com.techelevator.model.PlayDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlayDateDao implements PlayDateDao{

    @Autowired
    private JdbcLocationDao jdbcLocationDao;

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PlayDate> getAllPlayDates() {
        List<PlayDate> playDateList = new ArrayList<>();
        String sql = "SELECT play_date_id, host_pet_id, mate_pet_id, location_id, meeting_date, " +
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
            String sql = "SELECT play_date_id, host_pet_id, mate_pet_id, location_id, meeting_date, " +
                    "start_time, duration, mate_description, mate_size, status_id FROM play_dates " +
                    "WHERE location_id IN (SELECT location_id FROM location WHERE zipcode = ?)";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, zipcode);
            while (results.next()) {
                playDateList.add(mapToRowSet(results));
            }
            return playDateList;
    }

    //potential null pointer exception mate_pet_id
    @Override
    public List<PlayDate> getPlayDatesByUserId(long userId) {
        List<PlayDate> playDateListByUserId= new ArrayList<>();
        String sql= "SELECT play_date_id, host_pet_id, mate_pet_id, location_id, meeting_date, " +
                "start_time, duration, mate_description, mate_size, status_id FROM play_dates " +
                "WHERE host_pet_id IN (SELECT pet_id FROM user_pet WHERE user_id = ?)";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,userId);
        while (results.next()) {
            playDateListByUserId.add(mapToRowSet(results));
        }
        return playDateListByUserId;
    }

    @Override
    public void createPlayDate(PlayDate playDate) {
        String sql = "INSERT INTO play_dates (host_pet_id, location_id, meeting_date, start_time, duration, mate_description, mate_size, status_id) VALUES((SELECT pet_id FROM pets WHERE pet_id = ?),(SELECT location_id FROM location WHERE description = ?),?,?,?,?,?,?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playDate.getHostPetId(), playDate.getLocationId(), playDate.getMeetingDate(), playDate.getStartTime(), playDate.getDuration(), playDate.getMateDescription(), playDate.getMateSize(), 1);
    }

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    private PlayDate mapToRowSet(SqlRowSet results) {
        PlayDate playDate = new PlayDate();
        playDate.setPlayDateId(results.getLong("play_date_id"));
        playDate.setHostPetId(results.getLong("host_pet_id"));
        playDate.setMatePetId(results.getLong("mate_pet_id"));
        playDate.setLocationId(results.getLong("location_id"));
        playDate.setMeetingDate(results.getDate("meeting_date"));
        playDate.setStartTime(results.getTime("time").toLocalTime());
        playDate.setDuration(results.getLong("duration"));
        playDate.setMateDescription(results.getString("mate_description").split(","));
        playDate.setMateSize(results.getString("mate_size"));
        playDate.setStatusId(results.getLong("status_id"));

        return playDate;
    }
}
