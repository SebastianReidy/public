public class Location {

    private double latitude;
    private double longitude;
    private int floor;

    private final int EARTH_RADIUS = 6371;
    private static final double FLOOR_HEIGHT = 10;

    Location(double latitude, double longitude, int floor){
        this.latitude = latitude;
        this.longitude = longitude;
        this.floor = floor;
    }

    public double distanceFrom(Location location) {

        double latDistance = Math.toRadians(this.latitude - location.latitude);
        double lonDistance = Math.toRadians(this.longitude - location.longitude);

        double haversineTheta = haversine(latDistance)
                + Math.cos(Math.toRadians(location.latitude)) * Math.cos(Math.toRadians(this.latitude))
                * haversine(lonDistance);
        double theta = 2 * Math.atan2(Math.sqrt(haversineTheta), Math.sqrt(1 - haversineTheta));
        double distanceOnEarthBall = EARTH_RADIUS * theta * 1000; // convert to meters

        // approximate elevations between 2 floors to be 10m
        double elevationDiff = (location.floor - floor) * FLOOR_HEIGHT;

        double squaredDistance = Math.pow(distanceOnEarthBall, 2) + Math.pow(elevationDiff, 2);

        return Math.sqrt(squaredDistance);
    }

    private double haversine(double angle){
        return Math.pow(Math.sin(angle / 2), 2);
    }

}
