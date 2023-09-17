package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pl.bernat.Launcher;
import pl.bernat.model.WeatherService;
import pl.bernat.model.WeatherServiceFactory;
import pl.bernat.model.client.WeatherApi;
import pl.bernat.view.ViewFactory;

import java.net.URL;
import java.util.*;

public class WeatherForecastController extends BaseController implements Initializable {

    private int id;
    @FXML
    private ImageView editMainButton;

    @FXML
    private Label dateLabel;

    @FXML
    private Label cityNameLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private ImageView weatherIcon;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label cloudinessLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private  Label descriptionLabel;

    //next day forecast controls
    private List<Label> dates;
    @FXML
    private Label date1Label;

    @FXML
    private Label date2Label;

    @FXML
    private Label date3Label;

    @FXML
    private Label date4Label;

    private List<ImageView> icons;
    @FXML
    private ImageView icon1;

    @FXML
    private ImageView icon2;

    @FXML
    private ImageView icon3;

    @FXML
    private ImageView icon4;

    private List<Label> temperatures;
    @FXML
    private Label temperature1Label;

    @FXML
    private Label temperature2Label;

    @FXML
    private Label temperature3Label;

    @FXML
    private Label temperature4Label;

    public AnchorPane getWeatherForecastAnchorPane() {
        return weatherForecastAnchorPane;
    }

    @FXML
    private AnchorPane weatherForecastAnchorPane;
    private WeatherService weatherService = WeatherServiceFactory.createWeatherService();

    @FXML
    private HBox weatherForecastHBox;

    public WeatherForecastController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
        dates = new ArrayList<Label>(Arrays.asList(
                date1Label, date2Label, date3Label, date4Label
        ));
        icons = new ArrayList<ImageView>(Arrays.asList(
                icon1, icon2, icon3, icon4
        ));
        temperatures = new ArrayList<Label>(Arrays.asList(
                temperature1Label, temperature2Label, temperature3Label, temperature4Label
        ));
    }
    public void setMainCityName(String cityName) {
        cityNameLabel.setText(cityName);
    }
    public String getMainCityName(){
        return cityNameLabel.getText();
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
    @FXML
    void closeForecast() {
        viewFactory.closeForecast(id);
    }
    public void setAnchorPaneMargin(){
        HBox.setMargin(weatherForecastAnchorPane, new Insets(0, 20, 0,0));
    }

    public void checkWeather() {
        WeatherApi weather = weatherService.getWeather(getMainCityName());
        if(weather == null) {
            closeForecast();
        }
        displayWeather(weather);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
/*        WeatherApi weather = weatherService.getWeather(getMainCityName());
        displayWeather(weather);*/
    }

    private void displayWeather(WeatherApi weather) {
        cityNameLabel.setText(weather.getCity().getName());
        dateLabel.setText(weather.getList()[0].getDate());
        temperatureLabel.setText(weather.getList()[0].getMain().getTemperature() + "°C");
        humidityLabel.setText("Wilgotność: " + weather.getList()[0].getMain().getHumidity() + "%");
        windSpeedLabel.setText("Wiatr: " + weather.getList()[0].getWind().getSpeed() + " m/s");
        pressureLabel.setText("Ciśnienie: " + weather.getList()[0].getMain().getPressure() + " hPa");
        cloudinessLabel.setText("Zachmurzenie: " + weather.getList()[0].getClouds().getCloudiness() + "%");
        descriptionLabel.setText(weather.getList()[0].getWeather()[0].getDescription());
        weatherIcon.setImage(new Image("http://openweathermap.org/img/w/"+ weather.getList()[0].getWeather()[0].getIcon() + ".png"));
    }

    public void setWindow(int numberOfForecast) {
        setId(numberOfForecast);
        String cityName = Launcher.citiesList.get(numberOfForecast);
        cityNameLabel.setText(cityName);
        setAnchorPaneMargin();
        checkWeather();
    }
}
