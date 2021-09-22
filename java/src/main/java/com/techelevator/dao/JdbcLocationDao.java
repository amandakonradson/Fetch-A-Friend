package com.techelevator.dao;

import com.techelevator.model.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcLocationDao implements LocationDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcLocationDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<Location> getLocationByZipCode(long zipcode) {
        List<Location> locationList = new ArrayList<>();
        String sql = "SELECT description FROM location WHERE zipcode = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, zipcode);
        while(results.next()) {
            locationList.add(mapRowToLocation(results));
        }
        return locationList;
    }

    private Location mapRowToLocation(SqlRowSet results) {
        Location location = new Location();
        location.setDescription(results.getString("description"));
        location.setZipcode(results.getLong("zipcode"));

        return location;
    }
}
