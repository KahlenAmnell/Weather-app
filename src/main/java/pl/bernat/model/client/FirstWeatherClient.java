package pl.bernat.model.client;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FirstWeatherClient implements WeatherClient{
    @Override
    public WeatherApi getWeather(String cityName){
        WeatherApi obj = new WeatherApi();
        String request = "https://api.openweathermap.org/data/2.5/forecast?appid=609312d806745e844f4bff56016e6b13&units=metric&cnt=1&lang=pl" + "&q=" + cityName;

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(request))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String result = response.body();

            Gson gson = new Gson();
            obj = gson.fromJson(result, WeatherApi.class);
        } catch(IOException e){
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
