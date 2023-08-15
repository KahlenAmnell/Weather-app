package pl.bernat;


import javafx.application.Application;
import javafx.stage.Stage;
import pl.bernat.view.ViewFactory;

import java.io.IOException;
import java.util.ArrayList;


public class Launcher extends Application {
    private ArrayList<String> citiesList = new ArrayList<String>();

    @Override
    public void start(Stage stage) throws IOException {
/*        citiesList.add(new Cities("a", "a"));
        citiesList.add(new Cities("a", "a"));*/
        ViewFactory viewFactory = new ViewFactory(citiesList);
        viewFactory.showMainWindow();
    }


    public static void main(String[] args) {

        launch();
    }


}
