package pl.bernat.model;

import pl.bernat.model.client.FirstWeatherClient;
import pl.bernat.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService(){
        return new WeatherService(createWeatherClient());
    }
    private static WeatherClient createWeatherClient(){
        return new FirstWeatherClient();
    }
}
