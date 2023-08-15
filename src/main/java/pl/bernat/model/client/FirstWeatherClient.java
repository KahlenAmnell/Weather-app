package pl.bernat.model.client;

import pl.bernat.model.Weather;

public class FirstWeatherClient implements WeatherClient{
    @Override
    public Weather getWeather(String cityName) {
        return new Weather();
    }
}
