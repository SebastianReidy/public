import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Random;


import static org.hamcrest.Matchers.*;

public class StudentTest {

  @Test
  public void testAddition() {
    assertThat(1 + 1, Matchers.is(2));
  }

  @Test
  public void EmptyHashMapAfterAddStudentChooseSubjectRemoveStudent() {
    // Copied from the simulate method
    var random = new Random();

    var classes = new String[]{"SwEng", "SDP", "DB", "OS", "Algo", "ML"};
    var rooms = new String[]{"BC 05", "BC 06", "BC 07", "BC 08", "BC 09", "BC 10"};
    var students = Database.students().limit(100).toArray(Student[]::new);
    var locations = new HashMap<Student, String>();
    for (var student : students) {
      var location = rooms[random.nextInt(rooms.length)];
      locations.put(student, location);
    }

    // During the day, each student may (or may not) prepare for a random exam. However, some
    // students may decide to take a day off, have a coffee and not prepare for anything.
    for (var student : students) {
      if (random.nextBoolean()) {
        var course = classes[random.nextInt(classes.length)];
        student.take(course);
      }
    }

    // At the end of the day, all students leave their classrooms and go home.
    for (var student : students) {
      locations.remove(student);
    }

    assertThat(locations.isEmpty(), is(true));

  }
}
