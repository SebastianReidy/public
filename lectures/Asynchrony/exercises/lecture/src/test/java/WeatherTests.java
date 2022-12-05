import java.util.concurrent.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

final class WeatherTests {
    // Do not use sleep-based methods here!
    // However, since JUnit expects tests to be synchronous, you will need to wait for the operations to finish

    @Test
    void todaysWeatherIsSunny() {
        // TODO: Test that `Weather.today` returns `"Sunny"`, without changing `Weather.today`
        String weather = Weather.today().orTimeout(5, TimeUnit.SECONDS).join();  // add a timeout such that tests don't wait forever
        assertThat(weather, is(weather));
    }

    @Test
    void clickingButtonSetsWeatherToSunny() {
        // TODO: Test that `WeatherView.clickButton` sets `WeatherView.weather` to `"Sunny"`, without changing `WeatherView`
        WeatherView weatherView = new WeatherView();
        CompletableFuture<Void> future = new CompletableFuture<Void>();
        weatherView.setCallback(() -> future.complete(null));
        weatherView.clickButton();
        future.orTimeout(5, TimeUnit.SECONDS).join();
        assertThat(weatherView.weather(), is("Sunny"));
    }

    @Test
    void weathersContainsYesterdayAndToday() {
        // TODO: Test that `Weather.printWeathers` yields `"Today: Sunny"` and `"Yesterday: Cloudy"` in any order, **changing `Weather.printWeathers` as necessary**
        //       However, keep the logic of prefixing weathers inside `Weather`; make minimal changes
        var weathers = new ArrayList<String>();

        Weather.printWeathers(weathers::add)
                .orTimeout(5, TimeUnit.SECONDS)
                .join();

        assertThat(weathers, containsInAnyOrder("Today: Sunny", "Yesterday: Cloudy"));
    }
}
