package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
    public void createPet(Pet pet, Principal principal) {
        String sql= "INSERT INTO pets (name, breed, birth_year, gender, temperament, size, spayed_neutered) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,pet.getName(),pet.getBreed(),pet.getBirthYear(),pet.getGender(),pet.getTemperament(), pet.getSize(), pet.getSpayedNeutered());

        String sql2= "INSERT INTO user_pet (pet_id, user_id) VALUES ((SELECT pet_id FROM pets WHERE name = ?)," +
                " (SELECT user_id FROM users WHERE username = ?))";
        jdbcTemplate.update(sql2, pet.getName(), principal.getName());
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        String sql= "SELECT name, breed, birth_year, gender, temperament, size, spayed_neutered FROM pets ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            petList.add(mapRowtoPet(results));
        }
        return petList;
    }

    @Override
    public List<Pet> getPetsByUserId(long userId) {
        List<Pet> petsByUserId = new ArrayList<>();
        String sql= "SELECT pet_id, name, breed, birth_year, gender, temperament, size, spayed_neutered FROM pets WHERE pet_id IN (SELECT pet_id FROM user_pet WHERE user_id= ?) ";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,userId);
        while (results.next()){
            petsByUserId.add(mapRowtoPet(results));
        }
        return petsByUserId;

    }

    @Override
    public Pet getPetById(long petId) {
        Pet petById = new Pet();
        String sql= "SELECT name, breed, birth_year, gender, temperament, size, spayed_neutered FROM pets WHERE pet_id = ? ";
        SqlRowSet result= jdbcTemplate.queryForRowSet(sql,petId);
        if (result.next()){
            petById=mapRowtoPet(result);
        }
        return petById;
    }

    private Pet mapRowtoPet(SqlRowSet results){
        Pet pet = new Pet();
        pet.setPetId(results.getLong("pet_id"));
        pet.setName(results.getString("name"));
        pet.setBreed(results.getString("breed"));
        pet.setBirthYear(results.getInt("birth_year"));
        pet.setGender(results.getString("gender"));
        pet.setTemperament(results.getString("temperament"));
        pet.setSize(results.getString("size"));
        pet.setSpayedNeutered(results.getString("spayed_neutered"));

        return pet;
    }
}
