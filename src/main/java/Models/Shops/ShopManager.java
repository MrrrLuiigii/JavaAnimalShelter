package Models.Shops;

import DAL.DatabaseConnection;
import FX.AlertBox;
import Models.Animals.*;

import java.sql.SQLException;
import java.util.List;

public class ShopManager {

    private DatabaseConnection dbc;
    private Shop shop;

    public ShopManager() {
        this.shop = new Shop();
    }

    public boolean addProduct(Product product){
        if (product.getPrice() > 0){
            try
            {
                dbc = new DatabaseConnection();
                dbc.writeProduct(product);
                this.shop.getProducts().add(product);
            } catch (SQLException e)
            {
                new AlertBox().display("Error!", "Something went wrong writing to the database. Try again.");
            }
            return true;
        }
        return false;
    }

    public Shop getShop() {
        return shop;
    }
}
