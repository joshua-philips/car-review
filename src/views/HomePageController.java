package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class HomePageController implements Initializable {

    @FXML
    private AnchorPane holderPane;

    AnchorPane home;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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

}
