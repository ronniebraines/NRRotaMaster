package sample;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntroPageController implements Initializable {

    public Button continueButton;
    @FXML
    private Circle myCircle1, myCircle, myCircle2;
    @FXML
    private AnchorPane rootPane;


    @FXML
    void handleClick(ActionEvent event) {
        fadeOut();
    }

    private void fadeOut() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(400));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadNextScene();
            }
        });
        fadeTransition.play();
    }

    public void loadNextScene(){
        URL fxmlfile = getClass().getResource("loginpage.fxml");
        try {
            Parent secondView = FXMLLoader.load(fxmlfile);
            Scene nextPage = new Scene(secondView);
            Stage curStage = (Stage) rootPane.getScene().getWindow();
            curStage.setScene(nextPage);
        }catch (IOException ex){
            Logger.getLogger(IntroPageController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        translateNodeUp(myCircle1,1000, -25);
        translateNodeUp(myCircle,1100, -25);
        translateNodeUp(myCircle2,1200, -25);
        //giving the translate function the node, speed and distance to move
    }
    private void translateNodeUp (Node node, double duration, double distance){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(node);
        translate.setDuration(Duration.millis(duration));
        translate.setByY(distance);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setAutoReverse(true);
        translate.play();
    }
}
