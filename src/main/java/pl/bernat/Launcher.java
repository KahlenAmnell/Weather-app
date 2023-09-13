package pl.bernat;


import javafx.application.Application;
import javafx.stage.Stage;
import pl.bernat.model.WeatherService;
import pl.bernat.model.WeatherServiceFactory;
import pl.bernat.model.persistance.PersistenceAccess;
import pl.bernat.view.ViewFactory;

import java.util.ArrayList;
import java.util.List;


public class Launcher extends Application {
    public static ArrayList<String> citiesList = new ArrayList<String>();
    private PersistenceAccess persistenceAccess = new PersistenceAccess();

    @Override
    public void stop() throws Exception {
        persistenceAccess.saveToPersistence();
    }

    @Override
    public void start(Stage stage) {
        citiesList = persistenceAccess.loadFromPersistence();
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainWindow();
        if(citiesList.size() == 0){
            viewFactory.showCitySelectorWindow(0);
        }
    }


    public static void main(String[] args) {

        launch();
    }


}
