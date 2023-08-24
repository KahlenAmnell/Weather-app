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
        WeatherService weatherService = WeatherServiceFactory.createWeatherService();
        weatherService.getWeather("Gdańsk");

        ViewFactory viewFactory = new ViewFactory(citiesList);
        viewFactory.showMainWindow();
    }


    public static void main(String[] args) {

        launch();
    }


}
