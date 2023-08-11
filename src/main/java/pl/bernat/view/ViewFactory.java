package pl.bernat.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.bernat.controller.BaseController;
import pl.bernat.controller.MainWindowController;
import pl.bernat.controller.WeatherForecastController;
import pl.bernat.model.Cities;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ViewFactory {
    private ArrayList<Stage> activeStages;
    private ArrayList<Cities> citiesList;

    public ViewFactory(ArrayList<Cities> citiesList) {
        this.citiesList = citiesList;
        activeStages = new ArrayList<Stage>();
    }
    public void showMainWindow() throws IOException {
        BaseController controller = new MainWindowController(citiesList, this, "/FXMLFiles/MainWindow.fxml");
        initializeStage(controller);
    }
    private void initializeStage(BaseController baseController) throws IOException {
        FXMLLoader mainWindow = new FXMLLoader(getClass().getResource("/FXMLFiles/MainWindow.fxml"));
        mainWindow.setController(baseController);
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
        weatherForecastLoader.setController(new WeatherForecastController(citiesList, this, "/FXMLFiles/WeatherForecast.fxml"));

        weatherForecastLoader.setRoot(mainWindow.getNamespace().get("weatherForecastSpace"));
        weatherForecastLoader.load();

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
