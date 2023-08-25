package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import pl.bernat.model.WeatherService;
import pl.bernat.model.WeatherServiceFactory;
import pl.bernat.model.client.FirstWeatherClient;
import pl.bernat.model.client.WeatherApi;
import pl.bernat.view.ViewFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WeatherForecastController extends BaseController implements Initializable {

    private int id;
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

    @FXML
    private AnchorPane weatherForecastAnchorPane;
    private WeatherService weatherService = WeatherServiceFactory.createWeatherService();



    @FXML
    private HBox weatherForecastHBox;

    public WeatherForecastController(ArrayList<String> citiesList, ViewFactory viewFactory, String fxmlName) {
        super(citiesList, viewFactory, fxmlName);
    }
    public void setMainCityName(String cityName) {
        mainCityNameLabel.setText(cityName);
    }
    public String getMainCityName(){
        return mainCityNameLabel.getText();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    void editMainButton() {
        viewFactory.showCitySelectorWindow(id);
    }
    public void setAnchorPaneMargin(){
        HBox.setMargin(weatherForecastAnchorPane, new Insets(0, 20, 0,0));
    }

    public void checkWeather() {
        String name = getMainCityName();
        WeatherApi weather = weatherService.getWeather(getMainCityName());
        displayWeather(weather);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*        WeatherApi weather = weatherService.getWeather(getMainCityName());
        displayWeather(weather);*/
    }

    private void displayWeather(WeatherApi weather) {
        mainCityNameLabel.setText(weather.getCity().getName());
        mainCityDateLabel.setText(weather.getList()[0].getDate());
        mainCityTemperatureLabel.setText(weather.getList()[0].getMain().getTemperature() + "°C");
        mainHumidityLabel.setText("Wilgotność: " + weather.getList()[0].getMain().getHumidity() + "%");
        mainWindSpeedLabel.setText("Szybkość wiatru: " + weather.getList()[0].getWind().getSpeed() + "m/s");

    }
}
