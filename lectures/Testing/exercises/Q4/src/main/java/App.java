public final class App {
    public static void main(String[] args) {
        WeatherService weatherService = new WeatherService();
        Weather weather = weatherService.getWeatherToday(new RealHttpClient());
        System.out.println("Hello World!");
        System.out.println("The weather today is " + weather);
    }
}
