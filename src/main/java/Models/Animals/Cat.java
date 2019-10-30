package Models.Animals;

import Models.Enums.*;

public class Cat extends Animal {

    private String badHabits;

    public Cat(){
        super();
    }

    public Cat(String name, Gender gender, String badHabits) {
        super(name, gender);
        this.badHabits = badHabits;
    }

    public Cat(int id, String name, Gender gender, String badHabits)
    {
        super(id, name, gender);
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
}
