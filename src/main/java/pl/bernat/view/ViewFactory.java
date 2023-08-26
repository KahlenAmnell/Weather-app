package pl.bernat.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.bernat.controller.BaseController;
import pl.bernat.controller.CitySelectorController;
import pl.bernat.controller.MainWindowController;
import pl.bernat.controller.WeatherForecastController;
import pl.bernat.model.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private ArrayList<WeatherForecastController> forecasts;
    private ArrayList<Stage> activeStages;
    private ArrayList<String> citiesList;

    public ViewFactory(ArrayList<String> citiesList) {
        this.citiesList = citiesList;
        activeStages = new ArrayList<Stage>();
        forecasts = new ArrayList<WeatherForecastController>();
    }
    public void showMainWindow(){
        BaseController controller = new MainWindowController(citiesList, this, "/FXMLFiles/MainWindow.fxml");
        initializeStage(controller);
    }
    public void showCitySelectorWindow(int forecastId){
        BaseController controller = new CitySelectorController(citiesList, this, "/FXMLFiles/CitySelectorWindow.fxml", forecastId);
        initializeStage(controller);
    }

    private void initializeWeatherForecastWindows(FXMLLoader weatherForecastLoader, WeatherForecastController controller, int i){
try {
        weatherForecastLoader.load();
        controller.setId(i);
        forecasts.add(controller);
        if (i >= 1) {
            controller.setMainCityName(citiesList.get(i-1));
            controller.setAnchorPaneMargin();
            controller.checkWeather();
        } else {
            Platform.runLater(() -> {
                showCitySelectorWindow(controller.getId());
            });
        }
} catch (IOException e){
    e.printStackTrace();
}
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
                int i=citiesList.size();
                do {
                    setForecastWindows(window, i);
                    i--;
                } while (i > 0);
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

    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    private void setForecastWindows(FXMLLoader mainWindow, int i) throws IOException {
        WeatherForecastController weatherController = new WeatherForecastController(citiesList, this, "/FXMLFiles/WeatherForecast.fxml");
        FXMLLoader weatherForecastLoader = setFxmlLoader(weatherController);

        weatherForecastLoader.setRoot(mainWindow.getNamespace().get("weatherForecastSpace"));

        initializeWeatherForecastWindows(weatherForecastLoader, weatherController, i);
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
}
