import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

interface Model{
    public String get_weather();
}

public class RandomModel implements Model{

    RandomModel() {}

    public String get_weather() {
        int weather = ThreadLocalRandom.current().nextInt(0, 4);
        if (weather == 0) {
            return "Sunny";
        } else if (weather == 1) {
            return "Rainy";
        } else {
            return "???";
        }
    }
}

