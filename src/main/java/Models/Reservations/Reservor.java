package Models.Reservations;

import java.time.LocalDateTime;

public class Reservor {
    private int id;
    private String name;
    private LocalDateTime reservedAt;

    public Reservor(String name, LocalDateTime reservedAt) {
        this.name = name;
        this.reservedAt = reservedAt;
    }

    public Reservor(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.reservedAt = LocalDateTime.now();
    }

    public Reservor(int reservorId) {
        this.id = reservorId;
    }

    public int getId()
    {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
