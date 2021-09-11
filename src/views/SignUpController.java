package views;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import dbconnection.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    @FXML
    private AnchorPane parentPane;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXRadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXTextField location;

    @FXML
    private JFXButton signUp;

    @FXML
    private JFXButton login;

    @FXML
    private JFXRadioButton other;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement preparedStatement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setStyle("-fx-text-inner-color : #a0a2ab");
        password.setStyle("-fx-text-inner-color : #a0a2ab");
        location.setStyle("-fx-text-inner-color : #a0a2ab");

        handler = new DBHandler();
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        signUp.getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CarReview Login");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void signUpAction(ActionEvent event) {

        String insert = "INSERT INTO people (name, password, gender, location)" + "VALUES(?,?,?,?)";
        connection = handler.getConnection();

        if (!name.getText().isEmpty() || !password.getText().isEmpty()) {

            try {
                preparedStatement = connection.prepareStatement(insert);
                preparedStatement.setString(1, name.getText());
                preparedStatement.setString(2, password.getText());
                preparedStatement.setString(3, getGender());
                preparedStatement.setString(4, location.getText());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public String getGender() {
        String gen = male.isSelected() ? "Male" : female.isSelected() ? "Female" : "Other";
        return gen;
    }

}
