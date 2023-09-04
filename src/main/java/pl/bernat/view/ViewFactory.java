package pl.bernat.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.bernat.controller.BaseController;
import pl.bernat.controller.CitySelectorController;
import pl.bernat.controller.MainWindowController;
import pl.bernat.controller.WeatherForecastController;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private ArrayList<WeatherForecastController> forecasts;
    private ArrayList<Stage> activeStages;
    private ArrayList<String> citiesList;
    private MainWindowController mainWindow;
    private Object forecastRoot;

    public ViewFactory(ArrayList<String> citiesList) {
        this.citiesList = citiesList;
        activeStages = new ArrayList<Stage>();
        forecasts = new ArrayList<WeatherForecastController>();
    }
    public void showMainWindow(){
        BaseController controller = new MainWindowController(citiesList, this, "/FXMLFiles/MainWindow.fxml");
        mainWindow = (MainWindowController) controller;
        initializeStage(controller);
    }
    public void showCitySelectorWindow(int forecastId){
        BaseController controller = new CitySelectorController(citiesList, this, "/FXMLFiles/CitySelectorWindow.fxml", forecastId);
        initializeStage(controller);
    }
    public void showCitySelectorWindow(){
        BaseController controller = new CitySelectorController(citiesList, this, "/FXMLFiles/CitySelectorWindow.fxml");
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
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    private void showForecasts() {
        int i=citiesList.size();
        do {
            if(i==0){
                showCitySelectorWindow(0);
            }
            initializeForecastWindows();
            i--;
        } while (i > 0);
    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    public void initializeForecastWindows(){
        WeatherForecastController weatherController = new WeatherForecastController(citiesList, this, "/FXMLFiles/WeatherForecast.fxml");
        FXMLLoader weatherForecastLoader = setFxmlLoader(weatherController);

        weatherForecastLoader.setRoot(forecastRoot);

        try{
        weatherForecastLoader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        forecasts.add(weatherController);
        if(citiesList.get(weatherController.getId()) == null){
            System.out.println("tutaj");
            return;
        }
        if(!citiesList.isEmpty()) {
            weatherController.setWindow(forecasts.size() - 1);
        } else {
            System.out.println("gowno");
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
                    citiesList.remove(forecast.getMainCityName());
                    forecasts.remove(forecast);
                    mainWindow.resize(forecasts.size());
                    break;
                }
            }
        }

    }
}
