package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePageController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private JFXToolbar toolBar;

    @FXML
    private JFXButton toolBarRight;

    @FXML
    private Text welcome;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnExit;
    @FXML
    private AnchorPane holderPane;

    AnchorPane home;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // JFXRippler rippler = new JFXRippler(lblMenu);
        // rippler.setMaskType(JFXRippler.RipplerMask.RECT);
        // toolBarRight.getChildresn().add(rippler);

        createPage();
    }

    private void createPage() {
        try {
            home = FXMLLoader.load(getClass().getResource("Home.fxml"));
            setNode(home);
        } catch (LoadException le) {
            le.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }
    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add(node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();

    }

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        btnLogout.getScene().getWindow().hide();
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("CarReview Login");
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void homeAction(ActionEvent event) {

    }

}
