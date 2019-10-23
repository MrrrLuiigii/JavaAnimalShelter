package Resources;

import Models.Animals.*;
import Models.Enums.*;
import Models.Reservations.Reservation;

public class ConsoleMain
{
    public static void main(String[] args) {
        Reservation reservation = new Reservation();
        reservation.NewCat("Moppie", Gender.Female, "sneezing");
        reservation.NewDog("Mitch", Gender.Male);

        reservation.NewDog("Bolt", Gender.Male);

        for (Animal animal : reservation.getAnimals()){

            System.out.println(animal.toString());
        }
    }
}
