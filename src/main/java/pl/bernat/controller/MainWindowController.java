package pl.bernat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import pl.bernat.model.Cities;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public class MainWindowController extends BaseController {

    @FXML
    private Label closeLabel;
    @FXML
    private HBox weatherForecastSpace;

    public MainWindowController(ArrayList<Cities> citiesList, ViewFactory viewFactory, String fxmlName) {
        super(citiesList, viewFactory, fxmlName);
    }

    @FXML
    void closeAction() {

    }

}
