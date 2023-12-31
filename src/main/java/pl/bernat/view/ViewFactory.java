package pl.bernat.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.bernat.Launcher;
import pl.bernat.controller.BaseController;
import pl.bernat.controller.CitySelectorController;
import pl.bernat.controller.MainWindowController;
import pl.bernat.controller.WeatherForecastController;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private ArrayList<WeatherForecastController> forecasts;
    private ArrayList<Stage> activeStages;
    private MainWindowController mainWindow;
    private Object forecastRoot;

    public ViewFactory() {
        activeStages = new ArrayList<Stage>();
        forecasts = new ArrayList<WeatherForecastController>();
    }
    public void showMainWindow(){
        BaseController controller = new MainWindowController(this, "/FXMLFiles/MainWindow.fxml");
        mainWindow = (MainWindowController) controller;
        initializeStage(controller);
    }
    public void showCitySelectorWindow(int forecastId){
        BaseController controller = new CitySelectorController(this, "/FXMLFiles/CitySelectorWindow.fxml", forecastId);
        initializeStage(controller);
    }
    public void showCitySelectorWindow(){
        BaseController controller = new CitySelectorController(this, "/FXMLFiles/CitySelectorWindow.fxml");
        initializeStage(controller);
    }

    private FXMLLoader setFxmlLoader(BaseController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        loader.setController(controller);
        return loader;
    }
    private void initializeStage(BaseController baseController){
        FXMLLoader window = setFxmlLoader(baseController);

        Parent parent;
        try {
            parent = window.load();
            if(baseController.getClass() == MainWindowController.class){
                forecastRoot = window.getNamespace().get("weatherForecastSpace");
                showForecasts();
            }
        } catch (IOException e){
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("CSS/Style.css");
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    private void showForecasts() {
        int amountOfCities= Launcher.citiesList.size();
        do {
            initializeForecastWindows();
            amountOfCities--;
        } while (amountOfCities > 0);
    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    public void initializeForecastWindows(){
        WeatherForecastController weatherController = new WeatherForecastController(this, "/FXMLFiles/WeatherForecast.fxml");
        FXMLLoader weatherForecastLoader = setFxmlLoader(weatherController);

        weatherForecastLoader.setRoot(forecastRoot);

        try{
        weatherForecastLoader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        forecasts.add(weatherController);

        if(!Launcher.citiesList.isEmpty()) {
            weatherController.setWindow(forecasts.size() - 1);
        } else {
               // showCitySelectorWindow(0);
        }

    }

    public void updateCity(int forecastId, String cityName) {
        for(WeatherForecastController forecast: forecasts){
            int id = forecast.getId();
            if(id == forecastId){
                forecast.setMainCityName(cityName);
                forecast.checkWeather();
            }
        }
    }

    public void closeForecast(int id) {
        if(forecasts.size()>1) {
            for (WeatherForecastController forecast : forecasts) {
                if (id == forecast.getId()) {
                    mainWindow.getWeatherForecastSpace().getChildren().remove(forecast.getWeatherForecastAnchorPane());
                    Launcher.citiesList.remove(forecast.getMainCityName());
                    forecasts.remove(forecast);
                    mainWindow.resize(forecasts.size());
                    centerOnScreen(mainWindow.getStage());
                    break;
                }
            }
        }
    }

    public void addNewForecast(String cityName) {
        initializeForecastWindows();
        mainWindow.resize(forecasts.size());
        centerOnScreen(mainWindow.getStage());
    }
    public void centerOnScreen(Stage stage){
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double centerX = bounds.getMinX() + (bounds.getWidth() - stage.getWidth())/2;
        stage.setX(centerX);
    }

}
