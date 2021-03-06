package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("intropage.fxml"));
        Image icon = new Image("sample/nrlogo.png");
        primaryStage.setTitle("Rota Master");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 1015, 678));
        primaryStage.getIcons().add(icon); //setting the app icon as the network rail logo
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            launch(args);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
