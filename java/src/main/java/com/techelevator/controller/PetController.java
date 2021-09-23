package com.techelevator.controller;

import com.techelevator.dao.LocationDao;
import com.techelevator.dao.PetDao;
import com.techelevator.dao.PlayDateDao;
import com.techelevator.model.Location;
import com.techelevator.model.Pet;
import com.techelevator.model.PlayDate;
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
    @Autowired
    private PlayDateDao playDateDao;
    @Autowired
    private LocationDao locationDao;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createPlayDate")
    public void createPlayDate(@RequestBody PlayDate playDate, Location location){
        locationDao.addLocation(location);
        playDateDao.createPlayDate(playDate);
    }


}
