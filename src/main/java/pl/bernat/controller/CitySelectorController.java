package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public class CitySelectorController extends BaseController{
    @FXML
    private TextField cityTextField;
    private int forecastId = -1;
    public CitySelectorController(ArrayList<String> citiesList, ViewFactory viewFactory, String fxmlName) {
        super(citiesList, viewFactory, fxmlName);
    }
    public CitySelectorController(ArrayList<String> citiesList, ViewFactory viewFactory, String fxmlName, int forecastId){
        super(citiesList, viewFactory, fxmlName);
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
        if(forecastId == -1) {
            viewFactory.addNewForecast(getCityName());
        } else {
            viewFactory.updateCity(forecastId, getCityName());
        }
        closeAction();
    }

}
