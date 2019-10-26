package Models.Animals;

import Models.Enums.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dog extends Animal{

    private LocalDateTime lastWalk;
    private boolean needsWalk;
    private double price;

    public Dog(){
        super();
    }

    public Dog(AnimalType animalType, String name, Gender gender) {
        super(animalType, name, gender);
        this.lastWalk = LocalDateTime.now().withNano(0).withSecond(0);
    }

    public Dog(int id, AnimalType animalType, String name, Gender gender, Double price, LocalDateTime lastWalk, boolean needsWalk)
    {
        super(id, animalType, name, gender, price);
        this.lastWalk = lastWalk;
        this.needsWalk = needsWalk;
    }

    public void setNeedsWalk(boolean needsWalk)
    {
        this.needsWalk = needsWalk;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public LocalDateTime getLastWalk()
    {
        return lastWalk;
    }

    public void setLastWalk(LocalDateTime lastWalk) { this.lastWalk = lastWalk; }

    public boolean getNeedsWalk() {
        return (LocalDateTime.now().getDayOfYear() - this.lastWalk.getDayOfYear() > 0);
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return super.toString() + String.format(", last walk: " + this.lastWalk.format(formatter));
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }
}
