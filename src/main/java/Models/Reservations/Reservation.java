package Models.Reservations;

import Models.Animals.*;
import Models.Enums.*;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private List<Animal> animals = new ArrayList<>();

    public void NewCat(String name, Gender gender, String badHabits){
        this.animals.add(new Cat(AnimalType.Cat, name, gender, badHabits));
    }

    public void NewDog(String name, Gender gender){
        this.animals.add(new Dog(AnimalType.Dog, name, gender));
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
