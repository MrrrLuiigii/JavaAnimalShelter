package Models.Animals;

import Interfaces.ISellable;
import Models.Enums.*;
import Models.Reservations.Reservation;
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

    public AnimalType getAnimalType() { return animalType; }

    public void setAnimalType(AnimalType animalType) { this.animalType = animalType; }

    public void setName(String name) { this.name = name; }

    public void setGender(Gender gender) { this.gender = gender; }

    public Reservor getReservedBy() { return reservedBy; }

    public void setReservedBy (Reservor reservedBy){
        this.reservedBy = reservedBy;
    }

    public void setPrice(Reservation reservation) {

    }

    @Override
    public String getName()
    {
        return name;
    }

    public Gender getGender()
    {
        return gender;
    }

    @Override
    public Double getPrice()
    {
        return price;
    }

    @Override
    public void setPrice(Double price)
    {
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
