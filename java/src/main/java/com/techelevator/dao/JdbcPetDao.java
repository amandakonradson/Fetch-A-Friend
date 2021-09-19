package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcPetDao implements PetDao {
    // This may potentially need to be connected via DataSource, if the connection fails
    private JdbcTemplate jdbcTemplate;

    //public JdbcPetDao(JdbcTemplate jdbcTemplate) {
       // this.jdbcTemplate = jdbcTemplate;
   // }

    public JdbcPetDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createPet(Pet pet) {
        String sql= "INSERT INTO pets (name, breed, birth_year, gender, temperament, size, spayed_neutered) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,pet.getName(),pet.getBreed(),pet.getBirthYear(),pet.getGender(),pet.getTemperament(), pet.getSize(), pet.getSpayedNeutered());

    }
}
