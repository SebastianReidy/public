import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public interface HttpClient {

        public static HttpURLConnection realConnection(String jokeId) {
            URL url;
            try {
                url = new URL("https://icanhazdadjoke.com/j/" + jokeId);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Bad URL");
            }
            HttpURLConnection connection;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                throw new IllegalArgumentException("Cannot connect to jokes server");
            }
            connection.setRequestProperty("Accept", "text/plain");

            return connection;
        }
}
