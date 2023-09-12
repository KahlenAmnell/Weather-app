package pl.bernat;


import javafx.application.Application;
import javafx.stage.Stage;
import pl.bernat.model.WeatherService;
import pl.bernat.model.WeatherServiceFactory;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;


public class Launcher extends Application {
    private ArrayList<String> citiesList = new ArrayList<String>();

    @Override
    public void start(Stage stage) {
        citiesList.add("Gdańsk");
        citiesList.add("Dobrzykowice");
        citiesList.add("Brenna");
        ViewFactory viewFactory = new ViewFactory(citiesList);
        viewFactory.showMainWindow();
        if(citiesList.size() == 0){
            viewFactory.showCitySelectorWindow(0);
        }
    }


    public static void main(String[] args) {

        launch();
    }


}
