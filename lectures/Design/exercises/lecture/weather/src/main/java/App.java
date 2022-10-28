import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class App {
    // EXERCISE: Transform this basic app to use MVP instead of mixing user interface and business logic together
    //           Remember, MVP is "Model-View-Presenter", the View and Model should be completely decoupled (neither knows about the other),
    //           and the Presenter should only know about the interface of the View and Model.
    //           The View handles user input and forwards it to the Presenter, who then internally uses the Model to get data and updates the View.
    //           Your "main" method will be something like "new Presenter(new RandomModel(), new ConsoleView()).run();"
    //           You can run this app on the command line with `gradlew.bat run` on Windows or `./gradlew run` on macOS and Linux.

    public static void main(String[] args) {

        new Presenter(new RandomModel(), new ConsoleView()).run();

    }
}
