public class Location {
    private double locationLatitude;
    private double locationLongitude;
    private int floor;

    final int EARTH_RADIUS = 6371; // Radius of the earth


    Location(double latitude, double longitude, int floor){
        this.locationLatitude = latitude;
        this.locationLongitude = longitude;
        this.floor = floor;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double distance(Location other) {
        double latDistance = Math.toRadians(locationLatitude - other.getLocationLatitude());
        double lonDistance = Math.toRadians(locationLongitude - other.getLocationLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(other.getLocationLatitude())) * Math.cos(Math.toRadians(other.getLocationLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c * 1000; // convert to meters

        // approximate elevations between 2 floors to be 10m
        double elevationDiff = other.getFloor() * 10 - floor * 10;

        distance = Math.pow(distance, 2) + Math.pow(elevationDiff, 2);

        return Math.sqrt(distance); // could rename the variables and use a function / pow for the sin calculations
    }
}
