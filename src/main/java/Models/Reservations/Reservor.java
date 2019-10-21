package Models.Reservations;

import java.time.LocalDateTime;

public class Reservor {

    private String name;
    private LocalDateTime reservedAt;

    public Reservor(String name, LocalDateTime reservedAt) {
        this.name = name;
        this.reservedAt = reservedAt;
    }

    public String getName() {
        return name;
    }
}
