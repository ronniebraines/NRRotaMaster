package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("practice database");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            jdbc jdbc = new jdbc("");
            //test.joinTables("TBLWarrior", "TBLAllocation");
           // jdbc.updateTable("TBLWarrior", "warriorname", "Agathathecoolkid", "AGATHATHEMIFFED");
           // jdbc.alterTable("TBLWarrior" ,"WarriorID", "ID");

            ///jdbc.addNewColumn("TBLWarrior", "practicecollumn", "INT");
            jdbc.viewAll("TBLWarrior" , "ID");
            //jdbc.viewAllRecordsInCollumn("TBLWarrior", "ID");
            launch(args);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}