package sample;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Circle myCircle1, myCircle, myCircle2;
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
