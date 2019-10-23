package Models.Animals;

import Interfaces.ISellable;
import Models.Enums.*;
import Models.Reservations.Reservor;

import java.time.LocalDateTime;

public abstract class Animal implements ISellable {

    private AnimalType animalType;
    private String name;
    private Gender gender;
    private Reservor reservedBy;
    private Double price;

    public Animal(){}

    public Animal(AnimalType animalType, String name, Gender gender) {
        this.animalType = animalType;
        this.name = name;
        this.gender = gender;
    }

    public void setAnimalType(AnimalType animalType) { this.animalType = animalType; }

    public void setName(String name) { this.name = name; }

    public void setGender(Gender gender) { this.gender = gender; }

    public void setReservedBy (Reservor reservedBy){
        this.reservedBy = reservedBy;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean Reserve (String reservedBy){
        if (this.reservedBy == null) {
            this.reservedBy = new Reservor(reservedBy, LocalDateTime.now());
            return true;
        }

        return false;
    }

    @Override
    public String toString(){
        String reserved = "not reserved";

        if (this.reservedBy != null){
            reserved = String.format("reserved by %s", this.reservedBy.getName());
        }

        return String.format("%s, %s, %s", this.name, this.gender, reserved);
    }
}
