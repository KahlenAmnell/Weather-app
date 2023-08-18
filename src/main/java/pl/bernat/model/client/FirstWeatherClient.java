package pl.bernat.model.client;

import pl.bernat.model.WeatherApi;

public class FirstWeatherClient implements WeatherClient{
    @Override
    public WeatherApi getWeather(String cityName) {
        return new WeatherApi();
    }
}
