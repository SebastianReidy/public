public class App {
    public static void main(String[] args) {

        HttpClient realHttpClient = new HttpClient {
            @Override
            public List<String> get(String url) {
                URL url;
                List<String> jokes = new List<String>();
                try {
                    url = new URL("https://icanhazdadjoke.com/j/" + jokeId);
                } catch (MalformedURLException e) {
                    throw new IllegalArgumentException("Bad URL");
                }
                HttpURLConnection connection;
                try {
                    connection = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    jokes.add("Cannot connect to jokes server.");
                    return jokes;
                }
                connection.setRequestProperty("Accept", "text/plain");
                try (var connectionStream = connection.getInputStream();
                     var s = new Scanner(connectionStream).useDelimiter("\\A")) {
                    System.out.println(s.next());
                } catch (IOException e) {
                    jokes.add("Cannot fetch jokes.");
                }
                return jokes;
            }
        };

        new JokeFetcher().printJokeText("R7UfaahVfFd");
    }
}
