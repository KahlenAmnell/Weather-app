package pl.bernat;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.bernat.controller.MainWindowController;
import pl.bernat.controller.WeatherForecastController;
import pl.bernat.model.Cities;
import pl.bernat.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class Launcher extends Application {
    private ArrayList<Cities> citiesList = new ArrayList<Cities>();

    @Override
    public void start(Stage stage) throws IOException {
/*        citiesList.add(new Cities("a", "a"));
        citiesList.add(new Cities("a", "a"));*/
        ViewFactory viewFactory = new ViewFactory(citiesList);
        viewFactory.showMainWindow();
    }


    public static void main(String[] args) {

        launch();
    }


}
