package Models;

import Models.Animals.Animal;
import Models.Animals.Cat;
import Models.Animals.Dog;
import Models.Enums.Gender;
import Models.Reservations.Reservor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    private Animal reservedCat;
    private Animal reservedDog;
    private Animal cat;
    private Animal dog;

    @BeforeEach
    void setUp() {
        cat = new Cat("Rijvel", Gender.Male, "fighting");
        dog = new Dog("Bolt", Gender.Male);

        reservedCat = new Cat("Moppie", Gender.Female, "sneezing");
        reservedDog = new Dog("Mitch", Gender.Male);

        reservedCat.setReservedBy(new Reservor("Marian", LocalDateTime.of(2019, 10, 16, 17, 15)));
        reservedDog.setReservedBy(new Reservor("Freddy", LocalDateTime.of(2019, 10, 16, 17, 15)));
    }

    @Test
    void ReserveTest() {
        assertTrue(cat.Reserve("Nicky"));
        assertTrue(dog.Reserve("Nicky"));
    }

    @Test
    void ReserveTestAlreadyReserved(){
        assertFalse(reservedCat.Reserve("Nicky"));
        assertFalse(reservedDog.Reserve("Nicky"));
    }

    @Test
    void ToStringTest() {
        assertEquals("Moppie, Female, reserved by Marian, bad habits: sneezing", reservedCat.toString());
        assertEquals(String.format("Mitch, Male, reserved by Freddy, last walk: %s", LocalDateTime.now().withNano(0).withSecond(0)), reservedDog.toString());
    }
}