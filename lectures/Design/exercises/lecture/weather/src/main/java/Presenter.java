import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Presenter {
    private RandomModel model;
    private ConsoleView view;

    Presenter(RandomModel model, ConsoleView view){
        this.model = model;
        this.view = view;
    }

    public void run(){
        this.view.run(this);
    }

    public String get_weather() {
        String weather;
        do {
            weather = this.model.get_weather();
        } while (weather == "???");
        return weather;
    }
}