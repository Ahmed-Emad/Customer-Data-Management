package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;

public class Controller {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ToggleGroup toggleGroup;

    public void initialize() {
        Font mainFont = Font.loadFont("file:src/main/resources/fonts/Action_Man_Bold.ttf", 35);
        Font secondaryFont = Font.loadFont("file:src/main/resources/fonts/GoodDog.otf", 35);
        Font minorFont = Font.loadFont("file:src/main/resources/fonts/KomikaTitle-Paint.ttf", 25);

        welcomeLabel.setFont(mainFont);
        firstNameLabel.setFont(mainFont);
        lastNameLabel.setFont(mainFont);

        firstNameField.setFont(minorFont);
        lastNameField.setFont(minorFont);
    }

}
