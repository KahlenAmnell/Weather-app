package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import pl.bernat.model.Cities;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public class WeatherForecastController extends BaseController {
    @FXML
    private ImageView editMainButton;

    @FXML
    private Label mainCityDateLabel;

    @FXML
    private Label mainCityNameLabel;

    @FXML
    private Label mainCityTemperatureLabel;

    @FXML
    private ImageView mainCityWeatherIcon;

    @FXML
    private Label mainCountryNameLabel;

    @FXML
    private Label mainHumidityLabel;

    @FXML
    private Label mainNightTemperatureLabel;

    @FXML
    private Label mainPrecipitationProbabilityLabel;

    @FXML
    private Label mainWindSpeedLabel;

    public WeatherForecastController(ArrayList<Cities> citiesList, ViewFactory viewFactory, String fxmlName) {
        super(citiesList, viewFactory, fxmlName);
    }

    @FXML
    void editMainButton() {

    }

}
