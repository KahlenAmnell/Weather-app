package pl.bernat.model.client;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FirstWeatherClient implements WeatherClient{
    @Override
    public WeatherApi getWeather(String cityName) throws Exception {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.openweathermap.org/data/2.5/forecast?appid=609312d806745e844f4bff56016e6b13&q=Brenna&units=metric&cnt=1"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        String result = response.body();
        WeatherApi obj = new WeatherApi();
        try {
            Gson gson = new Gson();
             obj = gson.fromJson(result, WeatherApi.class);
            System.out.println("Temperature: " + obj.list[0].main.temperature);
            System.out.println("Wind speed: " + obj.list[0].wind.speed);
        } catch(Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
