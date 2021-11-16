package sample;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.awt.desktop.SystemSleepListener;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {


    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane menuInterface;

    @FXML
    private JFXHamburger hamOpener;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.setOpacity(0);
        FadeInTransition();
        menuInterface.setTranslateX(-340);
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamOpener);
        transition.setRate(-1);
        hamOpener.addEventHandler(MouseEvent.MOUSE_PRESSED, (event -> {
            if(transition.getRate() == -1){
                openSlide();
            }else{
                closeSlide();
            }
            transition.setRate(transition.getRate() * -1);
            transition.play();
        }));



//        Menu.setOnMouseClicked(event -> {
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(menuInterface);
//
//            slide.setToX(0);
//            slide.play();
//
//            menuInterface.setTranslateX(-340);
//
//            slide.setOnFinished((ActionEvent e) -> {
//                Menu.setVisible(false);
//                MenuBack.setVisible(true);
//            });
//        });
//
//        MenuBack.setOnMouseClicked(event -> {
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(menuInterface);
//
//            slide.setToX(-340);
//            slide.play();
//
//            menuInterface.setTranslateX(0);
//
//            slide.setOnFinished((ActionEvent e) -> {
//                Menu.setVisible(true);
//                MenuBack.setVisible(false);
//            });
//        });
}

    private void FadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anchorPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void openSlide() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(menuInterface);

        slide.setToX(0);
        slide.play();

        menuInterface.setTranslateX(-340);

    }

    private void closeSlide() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(menuInterface);

        slide.setToX(-340);
        slide.play();

        menuInterface.setTranslateX(0);
    }

}
