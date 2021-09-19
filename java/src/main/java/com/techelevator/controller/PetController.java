package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetDao petDao;

    @PostMapping("/addPuppy")
    public void createPet(@RequestBody Pet newDog){
        petDao.createPet(newDog);
    }




}
