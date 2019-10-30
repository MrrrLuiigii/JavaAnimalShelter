package Models.Animals;

import Interfaces.ISellable;
import Models.Enums.*;
import Models.Reservations.Reservation;
import Models.Reservations.Reservor;

import java.time.LocalDateTime;

public abstract class Animal {

    private int id;
    private String name;
    private Gender gender;
    private Reservor reservedBy;

    public Animal(){}

    public Animal(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public Animal(int id, String name, Gender gender)
    {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Reservor getReservedBy() { return reservedBy; }

    public void setReservedBy (Reservor reservedBy){
        this.reservedBy = reservedBy;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Gender getGender()
    {
        return gender;
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
