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
    private ArrayList<Stage> activeStages;
    private ArrayList<String> citiesList;

    public ViewFactory(ArrayList<String> citiesList) {
        this.citiesList = citiesList;
        activeStages = new ArrayList<Stage>();
    }
    public void showMainWindow(){
        BaseController controller = new MainWindowController(citiesList, this, "/FXMLFiles/MainWindow.fxml");
        initializeStage(controller);
    }
    public void citySelectorWindow(){
        BaseController controller = new CitySelectorController(citiesList, this, "/FXMLFiles/CitySelectorWindow.fxml");
        initializeStage(controller);
    }

    private void initializeWeatherForecastWindows(FXMLLoader weatherForecastLoader, WeatherForecastController controller) throws IOException {
        int i=citiesList.size();
try {
    do {
        weatherForecastLoader.load();
        i--;
        if (i >= 1) {
            controller.setAnchorPaneMargin();
        }
    } while (i > 0);
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
                setForecastWindows(window);
            }
        } catch (IOException e){
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void setForecastWindows(FXMLLoader mainWindow) throws IOException {
        WeatherForecastController weatherController = new WeatherForecastController(citiesList, this, "/FXMLFiles/WeatherForecast.fxml");
        FXMLLoader weatherForecastLoader = setFxmlLoader(weatherController);

        weatherForecastLoader.setRoot(mainWindow.getNamespace().get("weatherForecastSpace"));

        initializeWeatherForecastWindows(weatherForecastLoader, weatherController);
    }
}
