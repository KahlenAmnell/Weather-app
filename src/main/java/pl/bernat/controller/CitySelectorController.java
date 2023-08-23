package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public class CitySelectorController extends BaseController{
    @FXML
    private TextField cityTextField;
    public CitySelectorController(ArrayList<String> citiesList, ViewFactory viewFactory, String fxmlName) {
        super(citiesList, viewFactory, fxmlName);
    }

    @FXML
    void closeAction() {

    }

    @FXML
    void saveCityAction() {

    }

}
