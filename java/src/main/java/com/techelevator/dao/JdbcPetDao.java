package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcPetDao implements PetDao {
    // This may potentially need to be connected via DataSource, if the connection fails
    private JdbcTemplate jdbcTemplate;

    public JdbcPetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPet(Pet pet) {
        String sql= "INSERT INTO pets (name, breed, birth_year, gender, temperament) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,pet.getName(),pet.getBreed(),pet.getBirthYear(),pet.getGender(),pet.getTemperament());

    }
}
