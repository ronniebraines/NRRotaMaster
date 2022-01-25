package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPageController implements Initializable {
    @FXML
    private BorderPane rootPane1;
    @FXML
    private TextField usernameBox;
    @FXML
    private PasswordField passwordBox;
    @FXML
    private Button logInButton;


    public void mainPageTransfer(ActionEvent event) {
        fadeOut("MainPage.fxml");
    }

    private void transfer(){
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootPane1.setOpacity(0);
        FadeInTransition();
    }

    private void FadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(400));
        fadeTransition.setNode(rootPane1);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    private void fadeOut(String nextscene) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(400));
        fadeTransition.setNode(rootPane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadNextAnchorScene(nextscene);
            }
        });
        fadeTransition.play();
    }
    private void loadNextAnchorScene(String nextScene){
        try {
            Parent secondView;
            secondView = (AnchorPane) FXMLLoader.load(getClass().getResource(nextScene));
            Scene nextPage = new Scene(secondView);

            Stage curStage = (Stage) rootPane1.getScene().getWindow();
            curStage.setScene(nextPage);
        }catch (IOException ex){
            Logger.getLogger(IntroPageController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
