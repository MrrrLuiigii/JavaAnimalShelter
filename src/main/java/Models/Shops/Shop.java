package Models.Shops;

import DAL.DatabaseConnection;
import FX.AlertBox;
import Models.Animals.Animal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Shop {

    private DatabaseConnection dbc;
    private List<Product> products;

    public Shop() {
        getProducts();
    }

    public List<Product> getProducts() {
        try
        {
            dbc = new DatabaseConnection();
            this.products = dbc.getProducts();
        } catch (SQLException e)
        {
            new AlertBox().display("Error!", "Something went wrong loading products. Try again.");
        }

        return products;
    }
}
