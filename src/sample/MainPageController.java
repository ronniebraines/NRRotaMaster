package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button SELECT_BUTTON;

    @FXML
    private Button VIEW_BUTTON;

    @FXML
    private Button EDIT_BUTTON;

    @FXML
    private Button NEW_ROTA_BUTTON;

    @FXML
    void editCurrentRota(ActionEvent event) {

    }


    @FXML
    void generateNewRota(ActionEvent event) {

    }

    @FXML
    void selectCurrentWeek(ActionEvent event) {

    }

    @FXML
    void viewCurrentRota(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.setOpacity(0);
        FadeInTransition();
    }
    private void FadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anchorPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
