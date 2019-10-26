package Models.Shops;

import Interfaces.ISellable;

public class Product implements ISellable {
    private int id;
    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, Double price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("%s, %s", this.name, this.price);
    }
}
