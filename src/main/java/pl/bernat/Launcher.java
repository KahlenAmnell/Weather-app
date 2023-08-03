package pl.bernat;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.bernat.controller.MainWindowController;

import java.io.IOException;


public class Launcher extends Application {

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/MainWindow.fxml"));
        fxmlLoader.setController(new MainWindowController());
        Parent parent;
        try{
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


}
