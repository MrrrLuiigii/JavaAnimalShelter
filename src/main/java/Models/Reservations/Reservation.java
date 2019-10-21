package Models.Reservations;

import Models.Animals.Animal;
import Models.Animals.Cat;
import Models.Animals.Dog;
import Models.Enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private List<Animal> animals = new ArrayList<>();

    public void NewCat(String name, Gender gender, String badHabits){
        this.animals.add(new Cat(name, gender, badHabits));
    }

    public void NewDog(String name, Gender gender){
        this.animals.add(new Dog(name, gender));
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
