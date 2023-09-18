package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.bernat.Launcher;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public class MainWindowController extends BaseController {

    @FXML
    private AnchorPane mainWindowAnchorPane;

    @FXML
    private Label closeLabel;

    @FXML
    private HBox weatherForecastSpace;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public AnchorPane getMainWindowAnchorPane() {
        return mainWindowAnchorPane;
    }

    public HBox getWeatherForecastSpace() {
        return weatherForecastSpace;
    }

    @FXML
    void closeAction() {
        System.out.println(Launcher.citiesList);
        Stage stage = getStage();
        viewFactory.closeStage(stage);
    }
    public Stage getStage(){
        return (Stage) mainWindowAnchorPane.getScene().getWindow();
    }
    public void resize(int amountOfForecasts) {
        Stage stage = getStage();
        stage.setWidth(492+((385+20)*(amountOfForecasts-1)));
    }

    @FXML
    void addForecast() {
        viewFactory.showCitySelectorWindow();
        mainWindowAnchorPane.getScene().getWindow();
    }
}
