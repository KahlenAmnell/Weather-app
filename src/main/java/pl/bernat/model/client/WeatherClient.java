package pl.bernat.model.client;

public interface WeatherClient {
    WeatherApi getWeather(String cityName) throws Exception;
}
