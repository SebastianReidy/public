public class App {
    public static void main(String[] args) {
        String toPrint = new JokeFetcher().printJokeText(HttpClient.realConnection("R7UfaahVfFd"));
        System.out.println(toPrint);
    }
}
