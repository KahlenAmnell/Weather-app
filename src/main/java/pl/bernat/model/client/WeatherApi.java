package pl.bernat.model.client;
import com.google.gson.annotations.SerializedName;

public class WeatherApi {
    List list[];
    City city;
    public List[] getList() {
        return list;
    }

    public City getCity() {
        return city;
    }
    public class List{
        Main main;
        Weather weather[];
        Clouds clouds;
        Wind wind;
        @SerializedName("dt_txt")
        String date;
        public Main getMain() {
            return main;
        }

        public Weather[] getWeather() {
            return weather;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public String getDate() {
            return date;
        }
        public class Main{
            @SerializedName("temp")
            double temperature;
            @SerializedName("pressure")
            int pressure;

            @SerializedName("humidity")
            int humidity;
            public String getTemperature() {
                return (int) Math.round(temperature)+"";
            }

            public String getPressure() {
                return pressure+"";
            }

            public String getHumidity() {
                return humidity+"";
            }

        }
        public class Weather{
            @SerializedName("description")
            String description;
            public String getDescription() {
                return description;
            }
            @SerializedName("icon")
            String icon;
            public String getIcon(){return icon;}
        }
        public class Clouds{
            @SerializedName("all")
            int cloudiness;

            public String getCloudiness() {
                return cloudiness+"";
            }
        }
        public class Wind{
            @SerializedName("speed")
            double speed;

            public String getSpeed() {
                return speed+"";
            }
        }

    }
    public class City{
        @SerializedName("name")
        String name;

        public String getName() {
            return name;
        }
    }
}
