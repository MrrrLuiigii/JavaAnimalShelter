package Models.Animals;

import Models.Enums.Gender;

public class Cat extends Animal {

    private String badHabits;

    public Cat(String name, Gender gender, String badHabits) {
        super(name, gender);
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
