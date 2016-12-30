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
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:customer_data_management.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Account "
                    + "(id INTEGER PRIMARY KEY, first_name STRING, last_name STRING)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Address "
                    + "(id INTEGER PRIMARY KEY, address STRING, city STRING, state STRING, "
                    + "account_id INTEGER NOT NULL, FOREIGN KEY(account_id) REFERENCES Account(id))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Credit "
                    + "(id INTEGER PRIMARY KEY, type STRING, num STRING, exp_date STRING, "
                    + "account_id INTEGER NOT NULL, FOREIGN KEY(account_id) REFERENCES Account(id))");
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
        System.out.println(bounds.getWidth() + ", " + bounds.getHeight());
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
