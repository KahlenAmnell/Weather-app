package pl.bernat.model.persistance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class PersistenceAccess implements Serializable {
    private String CITIES_LOCATION = System.getProperty("user.home") + File.separator + "CitiesForWeatherForecast.ser";

    public void saveToPersistence(List<String> citiesList){
        try {
            File file = new File(CITIES_LOCATION);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(citiesList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
