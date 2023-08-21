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
    public void start(Stage stage) throws Exception {
/*        citiesList.add(new Cities("a", "a"));
        citiesList.add(new Cities("a", "a"));*/
        WeatherService weatherService = WeatherServiceFactory.createWeatherService();
        weatherService.getWeather("Gdańsk");

        ViewFactory viewFactory = new ViewFactory(citiesList);
        viewFactory.showMainWindow();
    }


    public static void main(String[] args) {

        launch();
    }


}
