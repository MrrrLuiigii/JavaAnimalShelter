package Models.Shops;

import Models.Animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Product> products;
    private List<Animal> animals;

    public Shop() {
        this.products = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void sellProduct(){

    }

    public void sellAnimal(){

    }
}
