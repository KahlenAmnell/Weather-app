package pl.bernat;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.bernat.controller.MainWindowController;
import pl.bernat.controller.WeatherForecastController;

import java.io.IOException;
import java.net.URL;


public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainWindow = new FXMLLoader(getClass().getResource("/FXMLFiles/MainWindow.fxml"));
        mainWindow.setController(new MainWindowController());
        Parent parent;
        try{
            parent = mainWindow.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);

        URL weatherForecast = getClass().getResource("/FXMLFiles/WeatherForecast.fxml");
        FXMLLoader weatherForecastLoader = new FXMLLoader(weatherForecast);
        weatherForecastLoader.setController(new WeatherForecastController());

            weatherForecastLoader.setRoot(mainWindow.getNamespace().get("weatherForecastSpace"));
            weatherForecastLoader.load();

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


}
