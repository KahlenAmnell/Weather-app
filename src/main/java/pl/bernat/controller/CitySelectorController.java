package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.bernat.Launcher;
import pl.bernat.model.WeatherService;
import pl.bernat.model.WeatherServiceFactory;
import pl.bernat.model.client.WeatherApi;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public class CitySelectorController extends BaseController{
    @FXML
    private TextField cityTextField;

    @FXML
    private Label errorLabel;
    private int forecastId = -1;
    private WeatherService weatherService = WeatherServiceFactory.createWeatherService();
    public CitySelectorController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    public CitySelectorController(ViewFactory viewFactory, String fxmlName, int forecastId){
        super(viewFactory, fxmlName);
        this.forecastId = forecastId;
    }
    public String getCityName() {
        return cityTextField.getText();
    }
    @FXML
    void closeAction() {
        Stage stage = (Stage) cityTextField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    public void saveCityAction() {
        WeatherApi weather = weatherService.getWeather(getCityName());
        if (weather == null) {
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);
            Launcher.citiesList.add(getCityName());
            if (forecastId == -1) {
                viewFactory.addNewForecast(getCityName());
            } else {
                viewFactory.updateCity(forecastId, getCityName());
            }
            closeAction();
        }
    }

}
