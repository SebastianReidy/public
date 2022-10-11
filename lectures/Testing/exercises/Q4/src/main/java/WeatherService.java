import java.io.IOException;

public class WeatherService {
    /* private final HttpClient httpClient;

    public WeatherService() {
        httpClient = new RealHttpClient();
    } */

    // This is not entirely correct! we still need the contructor for this class
    // In the solution they just add a second constructor which takes an HttpClient as input for testing

    public Weather getWeatherToday(HttpClient client) {
        String data;
        try {
            data = client.get("http://example.org/weather/today");
        } catch (IOException e) {
            return Weather.UNKNOWN;
        }

        switch (data) {
            case "Sunny":
                return Weather.SUNNY;

            case "Rainy":
                return Weather.RAINY;

            case "Snowy":
                return Weather.SNOWY;

            case "???":
                return Weather.ITS_RAINING_MEN_HALLELUJAH;

            default:
                return Weather.UNKNOWN;
        }
    }
}
