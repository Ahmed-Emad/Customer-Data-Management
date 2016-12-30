package main;

import account.Account;
import address.Address;
import credit.Credit;
import factory.CustomerFactory;
import factory.CustomerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;

public class Controller {

    @FXML
    private Label welcomeLabel;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton remoteRadio;
    @FXML
    private RadioButton localRadio;
    @FXML
    public Label accountLabel;
    @FXML
    public Label addressLabelM;
    @FXML
    public Label creditLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label stateLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label expLabel;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField expField;
    @FXML
    private Button saveButton;
    @FXML
    private Label logLabel;

    public void initialize() {
        Font mainFont_xl = Font.loadFont("file:src/main/resources/fonts/Action_Man_Bold.ttf", 36);
        Font mainFont_l = Font.loadFont("file:src/main/resources/fonts/Action_Man_Bold.ttf", 30);
        Font mainFont_s = Font.loadFont("file:src/main/resources/fonts/Action_Man_Bold.ttf", 26);
        Font secondaryFont = Font.loadFont("file:src/main/resources/fonts/GoodDog.otf", 30);
        Font minorFont = Font.loadFont("file:src/main/resources/fonts/KomikaTitle-Paint.ttf", 16);

        welcomeLabel.setFont(mainFont_xl);
        remoteRadio.setFont(secondaryFont);
        localRadio.setFont(secondaryFont);

        accountLabel.setFont(mainFont_l);
        addressLabelM.setFont(mainFont_l);
        creditLabel.setFont(mainFont_l);

        firstNameLabel.setFont(mainFont_s);
        lastNameLabel.setFont(mainFont_s);
        addressLabel.setFont(mainFont_s);
        cityLabel.setFont(mainFont_s);
        stateLabel.setFont(mainFont_s);
        typeLabel.setFont(mainFont_s);
        numberLabel.setFont(mainFont_s);
        expLabel.setFont(mainFont_s);
        logLabel.setFont(mainFont_l);

        firstNameField.setFont(minorFont);
        lastNameField.setFont(minorFont);
        addressField.setFont(minorFont);
        cityField.setFont(minorFont);
        stateField.setFont(minorFont);
        typeField.setFont(minorFont);
        numberField.setFont(minorFont);
        expField.setFont(minorFont);

        saveButton.setFont(mainFont_l);
    }

    public void handleSaveButton(ActionEvent event) {
        try {
            CustomerFactory customerFactory = null;
            String msg = "";
            if (remoteRadio.isSelected()) {
                customerFactory = CustomerUtil.getCustFactory("remote");
            }
            if (localRadio.isSelected()) {
                customerFactory = CustomerUtil.getCustFactory("local");
            }
            Account account = customerFactory.getAccount();
            account.setFirstName(firstNameField.getText());
            account.setLastName(lastNameField.getText());
            if (account.isValid()) {
                int accountId = account.save();
                System.out.println(accountId);
                Address address = customerFactory.getAddress();
                address.setAddress(addressField.getText());
                address.setCity(cityField.getText());
                address.setState(stateField.getText());
                if (address.isValid()) {
                    address.save(accountId);
                    Credit credit = customerFactory.getCreditCard();
                    credit.setType(typeField.getText());
                    credit.setNumber(numberField.getText());
                    credit.setExpDate(expField.getText());
                    if (credit.isValid()) {
                        credit.save(accountId);
                        msg = String.format("Saved: %s, %s", account.getFirstName(), account.getLastName());
                    } else {
                        msg = "Invalid Credit Card";
                    }
                } else {
                    msg = "Invalid Address";
                }
            } else {
                msg = "Invalid Account";
            }
            logLabel.setText(msg);
            welcomeLabel.requestFocus();
        } catch (Exception ignored) {
        }
    }

}
