package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.dao.PlayDateDao;
import com.techelevator.model.Pet;
import com.techelevator.model.PlayDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
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
    public List<Pet> getPetsByUser(@PathVariable long id){
        return petDao.getPetsByUserId(id);
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable("id") long petId){
        return petDao.getPetById(petId);
    }

    @GetMapping("/createPlayDate")
    public LocalDate getCurrentDate(){
        return playDateDao.getCurrentDate();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createPlayDate")
    public void createPlayDate(@RequestBody PlayDate playDate){
        playDateDao.createPlayDate(playDate);
    }


    @GetMapping("/allPlayDates/")
    public List<PlayDate> getAllPlayDates (){
        return playDateDao.getAllPlayDates();
    }

    @GetMapping("/allPlayDates/{id}")
    public List<PlayDate> getPlayDatesByUserid (@PathVariable("id") long userId){
        return playDateDao.getPlayDatesByUserId(userId);
    }

    @GetMapping("/allPlayDates/{zipcode}")
    public List<PlayDate> getPlayDatesByZip (@PathVariable long zipcode){
        return playDateDao.getPlayDatesByZipcode(zipcode);
    }




}
