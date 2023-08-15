package pl.bernat.model.client;

import pl.bernat.model.Cities;
import pl.bernat.model.Weather;

public interface WeatherClient {
    Weather getWeather(String cityName);
}
