package Models.Shops;

import Models.Animals.*;

import java.util.List;

public class ShopManager {
    private Shop shop;

    public ShopManager() {
        this.shop = new Shop();
    }

    public boolean addProduct(Product product){
        if (product.getPrice() > 0){
            this.shop.getProducts().add(product);
            return true;
        }

        return false;
    }

    public Shop getShop() {
        return shop;
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
        cat.setPrice(new Double((350 - (35 * len) < 35) ? (350 - (35 * len)) : 35));
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

        dog.setPrice(new Double((500 -(50 * dogs) < 50) ? (500 - (50 * dogs)) : 50));
        this.shop.getAnimals().add(dog);
    }
}
