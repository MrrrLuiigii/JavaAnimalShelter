package DAL;

import Models.Animals.Dog;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection
{
    Connection conn;

    public DatabaseConnection() throws SQLException
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost/phpmyadmin");
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("animalshelter");

        conn = (Connection) dataSource.getConnection();
    }

    public void writeDog(Dog dog) throws SQLException
    {
        String query = String.format("insert into dog " +
                "(name, gender, lastwalk, needswalk, price) " +
                String.format("values ('%s', '%s', '%s', '%s', '%s')",
                        dog.getName(), dog.getGender(), dog.getLastWalk(), dog.getNeedsWalk(), dog.getPrice()));

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
        conn.close();
    }
}
