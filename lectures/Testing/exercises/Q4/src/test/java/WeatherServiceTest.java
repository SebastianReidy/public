import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

public final class WeatherServiceTest {
    @Test
    void example() {
        assertThat(1 + 1, is(2));
    }

    @Test
    public void GetThrowsException(){
        HttpClient client = url -> {
            throw new IOException();
        };

        assertThat(new WeatherService(client).getWeatherToday(), is(Weather.UNKNOWN));
    }

    @Test
    public void SunnyWeather(){
        HttpClient client = url -> "Sunny";

        assertThat(new WeatherService(client).getWeatherToday(), is(Weather.SUNNY));
    }

    @Test
    public void RainyWeather(){
        HttpClient client = url -> "Rainy";

        assertThat(new WeatherService(client).getWeatherToday(), is(Weather.RAINY));
    }

    @Test
    public void SnowyWeather(){
        HttpClient client = url -> "Snowy";

        assertThat(new WeatherService(client).getWeatherToday(), is(Weather.SNOWY));
    }

    @Test
    public void QuestionWeather(){
        HttpClient client = url -> "???";

        assertThat(new WeatherService(client).getWeatherToday(), is(Weather.ITS_RAINING_MEN_HALLELUJAH));
    }

    @Test
    public void DefaultWeather(){
        HttpClient client = url -> "bla";

        assertThat(new WeatherService(client).getWeatherToday(), is(Weather.UNKNOWN));
    }


}
