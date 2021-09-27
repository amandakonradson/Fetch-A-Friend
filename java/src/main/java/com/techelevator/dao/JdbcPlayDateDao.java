package com.techelevator.dao;


import com.techelevator.model.PlayDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JdbcPlayDateDao implements PlayDateDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcPlayDateDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<PlayDate> getAllPlayDates() {
        List<PlayDate> playDateList = new ArrayList<>();
        String sql = "SELECT play_date_id, host_pet_id, mate_pet_id, location_street_address, location_city, location_zipcode, meeting_date, " +
                "start_time, duration, mate_description, mate_size, status_id FROM play_dates";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            playDateList.add(mapToRowSet(results));
        }
        return playDateList;
    }

    //9.27 currently not using delete or reuse later
    @Override
    public List<PlayDate> getPlayDatesByZipcode(long zipcode) {
            List<PlayDate> playDateList = new ArrayList<>();
            String sql = "SELECT play_date_id, host_pet_id, mate_pet_id, location_street_address, location_city, location_zipcode, meeting_date, " +
                    "start_time, duration, mate_description, mate_size, status_id FROM play_dates " +
                    "WHERE location_id IN (SELECT location_id FROM location WHERE zipcode = ?)";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, zipcode);
            while (results.next()) {
                playDateList.add(mapToRowSet(results));
            }
            return playDateList;
    }

    //play dates where the user is the host
    @Override
    public List<PlayDate> getPlayDatesByUserId(long userId) {
        List<PlayDate> playDateListByUserId= new ArrayList<>();
        String sql= "SELECT play_date_id, host_pet_id, mate_pet_id, location_street_address, location_city, location_zipcode, meeting_date, " +
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
        String sql = "INSERT INTO play_dates (host_pet_id, location_street_address, location_city, location_zipcode, " +
                "meeting_date, start_time, duration, mate_description, mate_size, status_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
        System.out.println(playDate);
        jdbcTemplate.update(sql, playDate.getHostPetId(), playDate.getLocationStreetAddress(),
                playDate.getLocationCity(), playDate.getLocationZipcode(), playDate.getMeetingDate(),
                playDate.getStartTime(), playDate.getDuration(), Arrays.toString(playDate.getMateDescription()), playDate.getMateSize(), 1);
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
        playDate.setLocationStreetAddress(results.getString("location_street_address"));
        playDate.setLocationCity(results.getString("location_city"));
        playDate.setLocationZipcode(results.getLong("location_zipcode"));
        playDate.setMeetingDate(results.getDate("meeting_date"));
        playDate.setStartTime(results.getTime("time").toLocalTime());
        playDate.setDuration(results.getLong("duration"));
        playDate.setMateDescription(results.getString("mate_description").split(","));
        playDate.setMateSize(results.getString("mate_size"));
        playDate.setStatusId(results.getLong("status_id"));

        return playDate;
    }
}
