package views;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dbconnection.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signUp;

    @FXML
    private JFXButton login;

    @FXML
    private JFXCheckBox remember;

    @FXML
    private JFXButton forgotPassword;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement preparedStatement;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setStyle("-fx-text-inner-color : #a0a2ab");
        password.setStyle("-fx-text-inner-color : #a0a2ab");

        handler = new DBHandler();

    }

    @FXML
    public void loginAction(ActionEvent event) {
        connection = handler.getConnection();
        String query = "SELECT * FROM people WHERE name=? AND password=?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username.getText());
            preparedStatement.setString(2, password.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            int count = 0;
            // String retrievedName = "";
            // int retrievedId = 0;
            while (resultSet.next()) {
                // retrievedName = resultSet.getString("name");
                // retrievedId = resultSet.getInt("idpeople");
                count++;
            }

            if (count == 1) {
                login.getScene().getWindow().hide();

                Stage home = new Stage();

                Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene scene = new Scene(root);
                home.setScene(scene);
                home.setResizable(false);
                home.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Username or password is incorrect");
                alert.show();
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void signUpAction(ActionEvent event) throws IOException {
        login.getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("CarReview Sign Up");
        stage.show();

    }

}
