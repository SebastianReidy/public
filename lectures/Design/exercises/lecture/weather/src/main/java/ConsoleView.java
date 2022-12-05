import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

interface View{
    void run(Presenter pres);
}

public class ConsoleView implements View{

    ConsoleView(){}

    public void run(Presenter pres){
        System.out.print("Hi! Hit Enter to check the weather forecast");
        var scanner = new Scanner(System.in);
        while (true) {
            scanner.nextLine();
            // In a real app, this would check the weather forecast using some API, but let's simulate a prediction instead
            String weather = pres.get_weather();

            System.out.println("Weather forecast: " + weather);
        }
    }
}