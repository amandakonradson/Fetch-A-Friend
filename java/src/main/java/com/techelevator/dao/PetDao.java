package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.security.Principal;

public interface PetDao {

    void createPet(Pet pet, Principal principal);

    // Pet updatePet(Pet pet);

}
