package pl.bernat.model.client;

import pl.bernat.model.WeatherApi;

public interface WeatherClient {
    WeatherApi getWeather(String cityName);
}
