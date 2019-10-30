package Models;

import Models.Animals.Animal;
import Models.Animals.Cat;
import Models.Animals.Dog;
import Models.Enums.AnimalType;
import Models.Enums.Gender;
import Models.Reservations.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation;
    private List<Animal> animals = new ArrayList<>();

    private Reservation emptyReservation;
    private List<Animal> emptyAnimals = new ArrayList();

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        animals = reservation.getAnimals();
        animals.add(new Cat("Moppie", Gender.Female, "sneezing"));
        animals.add(new Dog("Mitch", Gender.Male));

        emptyReservation = new Reservation();
        emptyAnimals = emptyReservation.getAnimals();
    }

    @Test
    void newCatTest() {
        reservation.NewCat("Doerak", Gender.Male, "being lazy");
        assertEquals(3, animals.size());
    }

    @Test
    void newCatToEmptyListTest() {
        emptyReservation.NewCat("Doerak", Gender.Male, "being lazy");
        assertEquals(1, emptyAnimals.size());
    }

    @Test
    void newDogTest() {
        reservation.NewDog("Bolt", Gender.Male);
        assertEquals(3, animals.size());
    }

    @Test
    void newDogToEmptyListTest() {
        emptyReservation.NewDog("Mitch", Gender.Male);
        assertEquals(1, emptyAnimals.size());
    }
}