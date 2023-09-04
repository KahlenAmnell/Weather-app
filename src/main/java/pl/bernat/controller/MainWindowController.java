package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public class MainWindowController extends BaseController {

    @FXML
    private AnchorPane mainWindowAnchorPane;

    @FXML
    private Label closeLabel;

    @FXML
    private HBox weatherForecastSpace;

    public MainWindowController(ArrayList<String> citiesList, ViewFactory viewFactory, String fxmlName) {
        super(citiesList, viewFactory, fxmlName);
    }

    public AnchorPane getMainWindowAnchorPane() {
        return mainWindowAnchorPane;
    }

    public HBox getWeatherForecastSpace() {
        return weatherForecastSpace;
    }

    @FXML
    void closeAction() {
        Stage stage = (Stage) mainWindowAnchorPane.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
    public void resize(int amountOfForecasts) {
        mainWindowAnchorPane.getScene().getWindow().setWidth(492+((385+20)*(amountOfForecasts-1)));
    }

    @FXML
    void addForecast() {
        viewFactory.showCitySelectorWindow();
    }
}
