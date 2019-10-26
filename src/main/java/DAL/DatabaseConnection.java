package DAL;

import Models.Animals.Animal;
import Models.Animals.Cat;
import Models.Animals.Dog;
import Models.Enums.AnimalType;
import Models.Enums.Gender;
import Models.Reservations.Reservor;
import Models.Shops.Product;
import com.google.gson.internal.$Gson$Preconditions;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection
{
    Connection conn;

    public DatabaseConnection()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("animalshelter");

        try
        {
            conn = (Connection) dataSource.getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void writeDog(Dog dog) throws SQLException
    {
        String query = "insert into dog " +
                //"(name, gender, lastwalk, needswalk, price) " +
                //"values (?, ?, ?, ?, ?)";
                "(name, gender, needswalk, price) " +
                "values (?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, dog.getName());
        pst.setString(2, dog.getGender().toString());
        //pst.setObject(3, dog.getLastWalk());
        //pst.setBoolean(4, dog.getNeedsWalk());
        //pst.setDouble(5, dog.getPrice());
        pst.setBoolean(3, dog.getNeedsWalk());
        pst.setDouble(4, dog.getPrice());

        pst.execute();
        pst.close();
        conn.close();

        if (dog.getReservedBy() != null){
            writeReservor(dog);
        }
    }

    private void writeReservor(Animal animal) throws SQLException
    {
        Reservor reservor = checkReservor(animal.getReservedBy());

        if (reservor == null){
            //call sp to write reservor and get id back
        }

        String query = "insert into reservation " +
                "(reservor_id, animal_id) " +
                "values (?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, animal.getReservedBy().getId());
        pst.setInt(2, animal.getId());

        pst.execute();
        pst.close();
        conn.close();
    }

    private Reservor checkReservor(Reservor reservedBy) throws SQLException
    {
        Reservor reservor = null;

        String query = "select id, name from reservor";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");

            reservor = new Reservor(id, name);
        }

        return reservor;
    }

    private void writeReservor(Cat cat){

    }

    public void writeCat(Cat cat) throws SQLException
    {
        String query = "insert into cat " +
                "(name, gender, badhabbits, price) " +
                "values (?, ?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, cat.getName());
        pst.setString(2, cat.getGender().toString());
        pst.setString(3, cat.getBadHabits());
        pst.setDouble(4, cat.getPrice());

        pst.execute();
        pst.close();
        conn.close();
    }

    public void writeProduct(Product product) throws SQLException
    {
        String query = "insert into product " +
                "(name, price) " +
                "values (?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, product.getName());
        pst.setDouble(2, product.getPrice());

        pst.execute();
        pst.close();
        conn.close();
    }

    public List<Product> getProducts() throws SQLException
    {
        List<Product> products = new ArrayList<>();

        String query = "select id, name, price from product";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Double price = rs.getDouble("price");
            Product product = new Product(id, name, price);
            products.add(product);
        }

        return products;
    }

    public List<Animal> getAnimals() throws SQLException
    {
        List<Animal> animals = new ArrayList<>();
        animals.addAll(getDogs());
        animals.addAll(getCats());
        return animals;
    }

    private List<Cat> getCats() throws SQLException
    {
        List<Cat> cats = new ArrayList<>();

        String query = "select id, name, gender, badhabbits, price from cat";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            Gender eGender = null;
            if (gender.equals("Male")){
                eGender = Gender.Male;
            }
            else{
                eGender = Gender.Female;
            }
            String badhabbits = rs.getString("badhabbits");
            Double price = rs.getDouble("price");
            Cat cat = new Cat(id, AnimalType.Cat, name, eGender, price, badhabbits);
            cats.add(cat);
        }

        return cats;
    }

    private List<Dog> getDogs() throws SQLException
    {
        List<Dog> dogs = new ArrayList<>();

        String query = "select id, name, gender, needswalk, price from dog";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String gender = rs.getString("gender");
            Gender eGender = null;
            if (gender.equals("Male")){
                eGender = Gender.Male;
            }
            else{
                eGender = Gender.Female;
            }
            Boolean needswalk = rs.getBoolean("needswalk");
            Double price = rs.getDouble("price");
            Dog dog = new Dog(id, AnimalType.Dog, name, eGender, price, LocalDateTime.now(), needswalk);
            dogs.add(dog);
        }

        return dogs;
    }
}
