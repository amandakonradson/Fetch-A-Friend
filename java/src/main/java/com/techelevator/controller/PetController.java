package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetDao petDao;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addPuppy")
    public void createPet(Principal principal, @RequestBody Pet newDog){
        petDao.createPet(newDog, principal);
    }

    @GetMapping("/allPets")
    public List<Pet> getAllPets(){
        return petDao.getAllPets();
    }

    @GetMapping("/userPets/{id}")
    public List<Pet> getPetsByUser(@PathVariable long userId){
        return petDao.getPetsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable long petId){
        return petDao.getPetById(petId);
    }



}
