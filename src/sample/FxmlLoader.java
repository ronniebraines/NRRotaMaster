package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;


public class FxmlLoader {
    private  Pane view;

    public Pane getPage(String fileName){
        try{
            URL fileUrl = Main.class.getResource("/sample/" + fileName + ".fxml");
            if (fileUrl == null){
                throw new java.io.FileNotFoundException("FXML file cannot be found");
            }
            view = new FXMLLoader().load(fileUrl);

        }catch (Exception e){
            System.out.println("No page " +fileName+ "please check FxmlLoader.");
        }
        return view;
    }
}
