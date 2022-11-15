import java.util.Map;

public class Room {
    private String name;  // RoomName class is just a string => unnecessary
    private Location location;
    private Map<TimeSlot, Course> occupancies;

    public Room(String name, double locationLatitude, double locationLongitude,
                int floor, Map<TimeSlot, Course> occupancies) {
        this.name = name;
        this.location = new Location(locationLatitude, locationLongitude, floor);
        this.occupancies = occupancies;
    }

    public boolean isAvailable() {
        return isAvailableAt(TimeSlot.now());
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
        return location.distanceFrom(new Location(otherLatitude, otherLongitude, otherFloor));
    }

    public Course.TYPE mostCommonCourseType() {
        int[] courseCounts = new int[Course.TYPE.values().length];
        for (Course c : occupancies.values()) {
            courseCounts[c.getType().ordinal()]++;
        }
        return Course.TYPE.values()[getMaxIndex(courseCounts)];
        // SOLUTION: use stream to find the max index, the list to find the index of this max value
    }

    private int getMaxIndex(int[] array){
        int maxIndex = 0;
        int maxValue = 0;
        int index = 0;
        for(int value : array){
            if (value > maxValue){
                maxIndex = index;
                maxValue = value;
            }
            index++;
        }
        return maxIndex;
    }
}