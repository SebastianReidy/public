import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class WeatherServiceTest {
    @Test
    void example() {
        assertThat(1 + 1, is(2));
    }

    // also test the case when the HttpClient does not provide any input, hence an exception is thrown
    @Test
    void weatherUnknown(){ // maybe add the name of the method under test to the test description
        HttpClient testClient = new HttpClient() {
            @Override
            public String get(String url) {
                return "asdfa";
            }
        };
        WeatherService weatherService = new WeatherService();
        assertThat(weatherService.getWeatherToday(testClient), is(Weather.UNKNOWN));
    }

    @Test
    void weatherSunny() {
        HttpClient testClient = new HttpClient() {
            @Override
            public String get(String url) {
                return "Sunny";
            }
        };

        WeatherService weatherService = new WeatherService();
        assertThat(weatherService.getWeatherToday(testClient), is(Weather.SUNNY));
    }

    @Test
    void weatherRainy() {
        HttpClient testClient = new HttpClient(){
            @Override
            public String get(String url) {
                return "Rainy";
            }
        }; // could also do the shortcut using a lambda function HttpClient testClient = url -> "Rainy";

        WeatherService weatherService = new WeatherService();
        assertThat(weatherService.getWeatherToday(testClient), is(Weather.RAINY));
    }

    @Test
    void weatherSnowy() {
        HttpClient testClient = new HttpClient(){
            @Override
            public String get(String url) {
                return "Snowy";
            }
        };

        WeatherService weatherService = new WeatherService();
        assertThat(weatherService.getWeatherToday(testClient), is(Weather.SNOWY));
    }

    @Test
    void weatherHaleluja() {
        HttpClient testClient = new HttpClient(){
            @Override
            public String get(String url) {
                return "???";
            }
        };

        WeatherService weatherService = new WeatherService();
        assertThat(weatherService.getWeatherToday(testClient), is(Weather.ITS_RAINING_MEN_HALLELUJAH));
    }

}
