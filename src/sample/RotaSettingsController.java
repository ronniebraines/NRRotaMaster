package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RotaSettingsController implements Initializable {

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

    public void initialize(URL location, ResourceBundle resources) {
        rotaTableView.setEditable(true);
        try {
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void populateTable() throws SQLException {
        //collumns are created by fxml
        TextFieldEditorBuilder textFieldEditorBuilder = new TextFieldEditorBuilder();
        ArrayList<Signalmen> signalmenList = JDBC.getSignalmenList();
        ObservableList<Signalmen> observableSignalmen = FXCollections.observableArrayList(signalmenList);

        firstNameCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Signalmen, String>("firstname")
        );
        firstNameCol.setCellFactory((TreeTableColumn<Signalmen, String> param) -> new GenericEditableTreeTableCell
                (textFieldEditorBuilder));
        firstNameCol.setOnEditCommit((TreeTableColumn.CellEditEvent<Signalmen, String> t) -> {
            String oldValue = (t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getFirstname());
            ((Signalmen) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).setFirstnameProperty((t.getNewValue()), false);
            checkCellforDatabase("Firstname", t.getNewValue(), oldValue);
        });

        lastNameCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Signalmen, String>("lastname")
        );
        lastNameCol.setCellFactory((TreeTableColumn<Signalmen, String> param) -> new GenericEditableTreeTableCell
                (textFieldEditorBuilder));
        lastNameCol.setOnEditCommit((TreeTableColumn.CellEditEvent<Signalmen, String> t) -> {
            String oldValue = (t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getLastname());
            ((Signalmen) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).setLastname((t.getNewValue()));
            checkCellforDatabase("Lastname", t.getNewValue(), oldValue);
        });
        emailCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Signalmen, String>("email")
        );
        emailCol.setCellFactory((TreeTableColumn<Signalmen, String> param) -> new GenericEditableTreeTableCell
                (textFieldEditorBuilder));
        emailCol.setOnEditCommit((TreeTableColumn.CellEditEvent<Signalmen, String> t) -> {
            String oldValue = (t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getEmail());
            ((Signalmen) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).setEmail((t.getNewValue()));
            checkCellforDatabase("Email", t.getNewValue(), oldValue);
        });
        numberCol.setCellValueFactory(
                new TreeItemPropertyValueFactory<Signalmen, String>("phone")
        );
        numberCol.setCellFactory((TreeTableColumn<Signalmen, String> param) -> new GenericEditableTreeTableCell
                (textFieldEditorBuilder));
        numberCol.setOnEditCommit((TreeTableColumn.CellEditEvent<Signalmen, String> t) -> {
            String oldValue = (t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getPhone());
            ((Signalmen) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).setPhone((t.getNewValue()));
            checkCellforDatabase("mobilenumber", t.getNewValue(), oldValue);
        });


        TreeItem<Signalmen> root = new RecursiveTreeItem<>(observableSignalmen, RecursiveTreeObject::getChildren);
        rotaTableView.setRoot(root);
        rotaTableView.setShowRoot(false);
    }

    @FXML
    void removeRowFromDB(ActionEvent event) throws SQLException {
        TreeItem<Signalmen> signalmen = rotaTableView.getSelectionModel().getSelectedItem();
        String lastname = signalmen.getValue().getLastname();
        int rowIndex = rotaTableView.getSelectionModel().getSelectedIndex();
        rotaTableView.getRoot().getChildren().remove(rowIndex);
        rotaTableView.getRoot().getChildren();//gets the index for the selected row
        JDBC.removeElement("SignalmenTBL", "Lastname", lastname);
    }


    public void checkCellforDatabase(String colName, String newVal, String oldValue) {
        if (!newVal.trim().isEmpty()) { //if the text field is not empty
            try {
                JDBC.updateTable("SignalmenTBL", colName, newVal, oldValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Is empty");
        }
    }

    @FXML
    void addSignalman(ActionEvent event) throws SQLException {
        JDBC.addSignalman();
        populateTable();
    }

    private void createSignalmanList() {
        ObservableList<Signalmen> data = FXCollections.observableArrayList(
        );
    }
}
