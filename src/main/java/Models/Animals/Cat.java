package Models.Animals;

import Models.Enums.*;

public class Cat extends Animal {

    private String badHabits;

    public Cat(){
        super();
    }

    public Cat(AnimalType animalType, String name, Gender gender, String badHabits) {
        super(animalType, name, gender);
        this.badHabits = badHabits;
    }

    public String getBadHabits() {
        return badHabits;
    }

    public void setBadHabits(String badHabits) {
        this.badHabits = badHabits;
    }

    @Override
    public String toString(){
        return super.toString() + String.format(", bad habits: %s", this.badHabits.toLowerCase());
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Double getPrice() {
        return null;
    }

    @Override
    public void setPrice(Double price) {

    }
}
