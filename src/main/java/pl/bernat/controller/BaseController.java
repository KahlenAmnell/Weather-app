package pl.bernat.controller;

import pl.bernat.view.ViewFactory;

import java.util.ArrayList;

public abstract class BaseController {
    protected ArrayList<String> citiesList;
    protected ViewFactory viewFactory;
    private String fxmlName;

    public String getFxmlName() {
        return fxmlName;
    }

    public BaseController(ArrayList<String> citiesList, ViewFactory viewFactory, String fxmlName) {
        this.citiesList = citiesList;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }
}
