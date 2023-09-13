package pl.bernat.model.persistance;

import pl.bernat.Launcher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceAccess implements Serializable {
    private String CITIES_LOCATION = System.getProperty("user.home") + File.separator + "CitiesForWeatherForecast.ser";

    public ArrayList<String> loadFromPersistence(){
        ArrayList<String> resultList = new ArrayList<String>();
        try{
            FileInputStream fileInputStream = new FileInputStream(CITIES_LOCATION);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<String> persistedList = (ArrayList<String>) objectInputStream.readObject();
            resultList.addAll(persistedList);
        } catch(Exception e){
            e.printStackTrace();
        }
        return resultList;
    }
    public void saveToPersistence(){
        try {
            File file = new File(CITIES_LOCATION);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(Launcher.citiesList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
