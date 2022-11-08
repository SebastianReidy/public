package example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * An abstraction to read Student records from a file.
 */
public class CSVReader {

    private final Path path;
    private final Cache cache;

    public CSVReader(String fileName, Cache cache) {

        this.path = Paths.get(fileName);
        this.cache = cache;

    }

    /**
     * Reads and returns the first n {@link Student} of the file.
     *
     * @param n the number of students to read.
     */
    public List<Student> read(int n) {
        List<Student> result = new ArrayList<>();

        for (int i = 1; i <= n; ++i) {
            if (cache.contains(i)) {
                result.add((Student)cache.get(i));
            }
        }
        if (result.size() == n) return result;

        try (Stream<String> stream = Files.lines(path)) {
            stream.skip(1).limit(n).forEach(line -> {
                if (!line.isEmpty()) {
                    String[] values = line.split("\t");
                    Student s = new Student(values[1], values[2], values[3], values[4]);
                    result.add(s);
                    cache.put(Integer.valueOf(values[0]), s);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns the first student of the file.
     */
    public Student head() {
        return read(1).get(0);
    }
}