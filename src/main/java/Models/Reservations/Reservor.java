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

    public Reservor(int id, String name, LocalDateTime reservedAt)
    {
        this.id = id;
        this.name = name;
        this.reservedAt = reservedAt;
    }

    public Reservor(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.reservedAt = LocalDateTime.now();
    }

    public int getId()
    {
        return id;
    }

    public String getName() {
        return name;
    }
}
