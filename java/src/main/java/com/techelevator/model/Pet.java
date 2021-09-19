package com.techelevator.model;

public class Pet {

    private long petId;
    private String name;
    private String breed;
    private int birthYear;
    private String gender;
    private String temperament;

    public Pet() {}

    public Pet(String name, String breed, int birthYear, String gender, String temperament) {
        this.name = name;
        this.breed = breed;
        this.birthYear = birthYear;
        this.gender = gender;
        this.temperament = temperament;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", birthYear=" + birthYear +
                ", gender='" + gender + '\'' +
                ", temperament='" + temperament + '\'' +
                '}';
    }
}
