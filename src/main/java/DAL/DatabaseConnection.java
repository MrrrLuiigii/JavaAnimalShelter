package DAL;

import FX.AlertBox;
import Models.Animals.Animal;
import Models.Animals.Cat;
import Models.Animals.Dog;
import Models.Enums.Gender;
import Models.Reservations.Reservor;
import Models.Shops.Product;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection
{
    public DatabaseConnection()
    {

    }

    public Connection getConnection(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("animalshelter");

        try
        {
            return (Connection) dataSource.getConnection();
        } catch (SQLException e)
        {
            new AlertBox().display("Error", "Something went wrong connecting to the database. Try again!");
        }

        return null;
    }

    public void writeDog(Dog dog) throws SQLException
    {
        Connection conn = getConnection();

        String query = "insert into dog " +
                "(name, gender, needswalk) " +
                "values (?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, dog.getName());
        pst.setString(2, dog.getGender().toString());
        pst.setBoolean(3, dog.getNeedsWalk());

        pst.execute();
        pst.close();
        conn.close();

        if (dog.getReservedBy() != null){
            writeReservor(dog);
        }
    }

    public void writeReservor(Animal animal) throws SQLException
    {
        Connection conn = getConnection();

        Reservor reservor = checkReservor(animal.getReservedBy());

        if (reservor == null){
            String query = "{call addReservor(?, ?)}";
            CallableStatement cst = conn.prepareCall(query);
            cst.setString(1, animal.getReservedBy().getName());
            cst.registerOutParameter(2, Types.INTEGER);
            cst.executeUpdate();

            if (cst.getInt("pId") != 0){
                reservor = new Reservor(cst.getInt("pId"), animal.getReservedBy().getName());
            }
        }

        String query = "insert into reservation " +
                "(reservor_id, animal_id) " +
                "values (?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, reservor.getId());
        pst.setInt(2, animal.getId());

        pst.execute();
        pst.close();
        conn.close();
    }

    private Reservor checkReservor(Reservor reservedBy) throws SQLException
    {
        Connection conn = getConnection();

        String query = "select id, name from reservor";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");

            if (reservedBy.getName().equals(name) && reservedBy.getId() == id){
                return new Reservor(id, name);
            }
        }

        return null;
    }

    public void writeCat(Cat cat) throws SQLException
    {
        Connection conn = getConnection();

        String query = "insert into cat " +
                "(name, gender, badhabbits) " +
                "values (?, ?, ?)";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, cat.getName());
        pst.setString(2, cat.getGender().toString());
        pst.setString(3, cat.getBadHabits());

        pst.execute();
        pst.close();
        conn.close();
    }

    public void writeProduct(Product product) throws SQLException
    {
        Connection conn = getConnection();

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
        Connection conn = getConnection();

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
        Connection conn = getConnection();

        List<Cat> cats = new ArrayList<>();

        String query = "select id, name, gender, badhabbits from cat";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Gender gender = Gender.valueOf(rs.getString("gender"));
            String badhabbits = rs.getString("badhabbits");
            Cat cat = new Cat(id, name, gender, badhabbits);
            cat = (Cat) getReservorId(cat);
            cats.add(cat);
        }

        return cats;
    }

    private List<Dog> getDogs() throws SQLException
    {
        Connection conn = getConnection();

        List<Dog> dogs = new ArrayList<>();

        String query = "select id, name, gender, needswalk from dog";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Gender gender = Gender.valueOf(rs.getString("gender"));
            Boolean needswalk = rs.getBoolean("needswalk");
            Dog dog = new Dog(id, name, gender, LocalDateTime.now(), needswalk);
            dog = (Dog) getReservorId(dog);
            dogs.add(dog);
        }

        return dogs;
    }

    private Animal getReservorId(Animal animal) throws SQLException
    {
        Connection conn = getConnection();

        String query = "select reservor_id from reservation where animal_id = ?";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, animal.getId());

        ResultSet rs = pst.executeQuery();

        int reservorId = 0;

        while (rs.next()){
            reservorId = rs.getInt("reservor_id");
        }

        if (reservorId != 0){
            animal.setReservedBy(getReservor(reservorId));
        }

        pst.close();
        conn.close();

        return animal;
    }

    private Reservor getReservor(int reservorId) throws SQLException
    {
        Connection conn = getConnection();

        Reservor reservor = new Reservor(reservorId);

        String query = "select name from reservor where id = ?";

        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, reservorId);

        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            reservor.setName(rs.getString("name"));
        }

        return reservor;
    }
}
