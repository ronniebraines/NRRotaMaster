package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.cell.*;
import sample.JDBC;
import sample.Signalmen;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {


    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane tableStackPane;

    @FXML
    private VBox menuInterface;
    @FXML
    private JFXButton removeButton;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton viewRotaButton;
    @FXML
    private JFXHamburger hamOpener;
    @FXML
    private JFXTreeTableView<Signalmen> rotaTableView = new JFXTreeTableView<>();
    @FXML
    private TreeTableColumn<Signalmen, String> emailCol;
    @FXML
    private TreeTableColumn<Signalmen, String> lastNameCol;
    @FXML
    private TreeTableColumn<Signalmen, String> firstNameCol;
    @FXML
    private TreeTableColumn<Signalmen, String> numberCol;

    private String oldValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.setOpacity(0);
        FadeInTransition();
        menuInterface.setTranslateX(-340);
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamOpener);
        transition.setRate(-1);
        hamOpener.addEventHandler(MouseEvent.MOUSE_PRESSED, (event -> {
            if (transition.getRate() == -1) {
                openSlide();
            } else {
                closeSlide();
            }
            transition.setRate(transition.getRate() * -1);
            transition.play();
        }));
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


    @FXML
    void switchToRotaSettings(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("rotaSettings");
        borderPane.setCenter(view);
    }

    @FXML
    void switchToCalendar(ActionEvent event) {
        FxmlLoader loader = new FxmlLoader();
        Pane view = loader.getPage("calendar");
        borderPane.setCenter(view);
    }
}