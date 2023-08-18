package pl.bernat.model;
import com.google.gson.annotations.SerializedName;

public class WeatherApi {
    List list[];
    City city;
    public class List{
        Main main;
        Weather weather[];
        Clouds clouds;
        Wind wind;
        @SerializedName("dt_txt")
        String date;
        public class Main{
            @SerializedName("temp")
            int temperature;
            @SerializedName("pressure")
            int pressure;
            @SerializedName("humidity")
            int humidity;
        }
        public class Weather{
            @SerializedName("description")
            String description;
        }
        public class Clouds{
            @SerializedName("all")
            int cloudiness;
        }
        public class Wind{
            @SerializedName("speed")
            double speed;
        }

    }
    public class City{
        @SerializedName("name")
        String name;
    }
}
