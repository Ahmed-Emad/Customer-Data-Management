package main;

import com.guigarage.flatterfx.FlatterFX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    static final String welcome = "Welcome";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FlatterFX.style();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Customer Data Management");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        Scene scene = new Scene(root, 600, 550);
        primaryStage.setScene(scene);
        primaryStage.show();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:customer_data_management.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Account "
                    + "(id INTEGER PRIMARY KEY, first_name STRING, last_name STRING)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Address "
                    + "(id INTEGER PRIMARY KEY, address STRING, city STRING, state STRING)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Credit "
                    + "(id INTEGER PRIMARY KEY, type STRING, num STRING, exp_date STRING)");
//            statement.executeUpdate("INSERT INTO Account (first_name, last_name) VALUES('Ahmed', 'Emad')");
//            statement.executeUpdate("INSERT INTO Address (address, city, state) VALUES('35 Salah Zohny', 'Alexandria', 'Alexandria')");
//            statement.executeUpdate("INSERT INTO Credit (type, num, exp_date) VALUES('Visa', '12345678', '10/18')");
            ResultSet rs = statement.executeQuery("SELECT * FROM ACCOUNT WHERE "
                    + "first_name='" + "Ahmed" + "' AND last_name='" + "Emad" + "'");
            System.out.println("id = " + rs.getInt("id"));
            rs = statement.executeQuery("SELECT * FROM ACCOUNT");
            while (rs.next()) {
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("first_name = " + rs.getString("first_name"));
                System.out.println("last_name = " + rs.getString("last_name"));
            }
            rs = statement.executeQuery("SELECT * FROM Address");
            while (rs.next()) {
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("address = " + rs.getString("address"));
                System.out.println("city = " + rs.getString("city"));
                System.out.println("state = " + rs.getString("state"));
            }
            rs = statement.executeQuery("SELECT * FROM Credit");
            while (rs.next()) {
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("type = " + rs.getString("type"));
                System.out.println("num = " + rs.getString("num"));
                System.out.println("exp_date = " + rs.getString("exp_date"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

}
