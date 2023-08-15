package pl.bernat.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.bernat.controller.BaseController;
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
    public void showMainWindow() throws IOException {
        BaseController controller = new MainWindowController(citiesList, this, "/FXMLFiles/MainWindow.fxml");
        initializeStage(controller);
    }

    private void initializeWeatherForecastWindows(FXMLLoader weatherForecastLoader, WeatherForecastController controller) throws IOException {
        int i=citiesList.size();

        do {
            weatherForecastLoader.load();
            i--;
            if(i >= 1){
                controller.setAnchorPaneMargin();
            }
        } while (i > 0);
    }
    private FXMLLoader setFxmlLoader(BaseController controller){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        loader.setController(controller);
        return loader;
    }
    private void initializeStage(BaseController baseController) throws IOException {
        FXMLLoader mainWindow = setFxmlLoader(baseController);

        Scene scene = new Scene(mainWindow.load());

        WeatherForecastController weatherController = new WeatherForecastController(citiesList, this, "/FXMLFiles/WeatherForecast.fxml");
        FXMLLoader weatherForecastLoader = setFxmlLoader(weatherController);

        weatherForecastLoader.setRoot(mainWindow.getNamespace().get("weatherForecastSpace"));

        initializeWeatherForecastWindows(weatherForecastLoader, weatherController);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
