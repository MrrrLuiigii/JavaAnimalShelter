package Models.Reservations;

import DAL.DatabaseConnection;
import FX.AlertBox;
import Models.Animals.*;
import Models.Enums.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private DatabaseConnection dbc;
    private List<Animal> animals = new ArrayList<>();

    public void NewCat(String name, Gender gender, String badHabits){
        Cat cat = new Cat(name, gender, badHabits);

        try
        {
            dbc = new DatabaseConnection();
            dbc.writeCat(cat);
            this.animals.add(cat);
        } catch (SQLException e)
        {
            new AlertBox().display("Error!", "Something went wrong writing to the database. Try again.");
        }
    }

    public void NewDog(String name, Gender gender){
        Dog dog = new Dog(name, gender);

        try
        {
            dbc = new DatabaseConnection();
            dbc.writeDog(dog);
            this.animals.add(dog);
        } catch (SQLException e)
        {
            new AlertBox().display("Error!", "Something went wrong writing to the database. Try again.");
        }
    }

    public List<Animal> getAnimals() {
        try
        {
            dbc = new DatabaseConnection();
            this.animals = dbc.getAnimals();
        } catch (SQLException e)
        {
            new AlertBox().display("Error!", "Something went wrong loading animals. Try again.");
        }

        return animals;
    }

    public boolean reserveAnimal(Animal animal, String reservor){
        if (animal.getReservedBy() == null){
            animal.setReservedBy(new Reservor(reservor, LocalDateTime.now()));

            try
            {
                dbc = new DatabaseConnection();
                dbc.writeReservor(animal);
            } catch (Exception e) {
                new AlertBox().display("Error!", "Something went wrong reserving this animals. Try again.");
            }


            return true;
        }

        return false;
    }
}
