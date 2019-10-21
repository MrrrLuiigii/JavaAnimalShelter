package Models.Shops;

import Models.Animals.*;

import java.util.List;

public class ShopManager {
    private Shop shop;

    public ShopManager() {
        this.shop = new Shop();
    }

    public boolean addProduct(String name, String description, float price){
        if (price > 0){
            this.shop.getProducts().add(new Product(name, description, price));
            return true;
        }

        return false;
    }

    public boolean addAnimal(Animal animal){
        if (animal instanceof Dog){
            addDog(animal);
            return true;
        }
        else if (animal instanceof Cat){
            addCat(animal);
            return true;
        }

        return false;
    }

    private void addCat(Animal cat) {
        int len = ((Cat) cat).getBadHabits().length();
        cat.setPrice((350 - (35 * len) < 35) ? (350 - (35 * len)) : 35);
        this.shop.getAnimals().add(cat);
    }

    private void addDog(Animal dog){
        List<Animal> animals = this.shop.getAnimals();

        int dogs = 0;

        for (Animal a : animals){
            if (a instanceof Dog){
                dogs++;
            }
        }

        dog.setPrice((500 -(50 * dogs) < 50) ? (500 - (50 * dogs)) : 50);
        this.shop.getAnimals().add(dog);
    }
}