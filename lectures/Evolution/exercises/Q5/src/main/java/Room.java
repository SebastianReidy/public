import java.util.Map;

public class Room {
    private String name;
    private Location location;
    private Map<TimeSlot, Course> occupancies;

    public Room(String name, double locationLatitude, double locationLongitude,
                int floor, Map<TimeSlot, Course> occupancies) {
        this.name = name;
        this.location = new Location(locationLatitude, locationLongitude, floor);
        this.occupancies = occupancies;
    }

    public boolean isAvailable() {
        TimeSlot now = TimeSlot.now(); // returns the TimeSlot that we're currently in
        return isAvailableAt(now);
    }

    public boolean isAvailableAt(TimeSlot slot) {
        boolean temp = true;
        for (TimeSlot s : occupancies.keySet()) {
            if (s.equals(slot)) {
                temp = false;
            }
        }
        return temp;
    }

    public String getName() {
        return name;
    }

    // distance in meters from other to this room's location
    public double distanceFrom(double otherLatitude, double otherLongitude, int otherFloor) {
        return location.distance(new Location(otherLatitude, otherLongitude, otherFloor));
    }

    public Course.TYPE mostCommonCourseType() {
        int[] courseCount = new int[Course.TYPE.values().length];

        for(Course c : occupancies.values()){
            courseCount[Course.getType().ordinal()]++;
        }

        Course.TYPE currentBest = Course.TYPE.ART;
        int bestCount = 0;
        for(int i=0;i<Course.TYPE.values().length;i++){
            if (courseCount[i] > bestCount){
                bestCount = courseCount[i];
                currentBest = Course.TYPE.values()[i];
            }
        }

        return currentBest;
    }
}