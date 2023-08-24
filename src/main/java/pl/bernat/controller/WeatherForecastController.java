package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public class WeatherForecastController extends BaseController {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @FXML
    void editMainButton() {
        viewFactory.showCitySelectorWindow(id);
    }
    public void setAnchorPaneMargin(){
        HBox.setMargin(weatherForecastAnchorPane, new Insets(0, 20, 0,0));
    }

}
