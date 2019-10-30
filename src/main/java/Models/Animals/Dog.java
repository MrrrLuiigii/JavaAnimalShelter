package Models.Animals;

import Models.Enums.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dog extends Animal{

    private LocalDateTime lastWalk;
    private boolean needsWalk;

    public Dog(){
        super();
    }

    public Dog(String name, Gender gender) {
        super(name, gender);
        this.lastWalk = LocalDateTime.now().withNano(0).withSecond(0);
    }

    public Dog(int id, String name, Gender gender, LocalDateTime lastWalk, boolean needsWalk)
    {
        super(id, name, gender);
        this.lastWalk = lastWalk;
        this.needsWalk = needsWalk;
    }

    public void setNeedsWalk(boolean needsWalk)
    {
        this.needsWalk = needsWalk;
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
}
