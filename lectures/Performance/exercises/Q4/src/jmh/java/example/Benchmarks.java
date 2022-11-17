package example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.*;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class Benchmarks{

    @Benchmark
    public void baselineBenchmark(Blackhole bh) {
        for (int i=0;i<10;i++){
            var reader = new CSVReader("res/students.txt");
            bh.consume(reader.read(10));
        }
    }

    /**
     * SOLUTION: The task says we should use the first element on the file line. We are using the first element of the
     * student object. The first value of the Studnet object is ambiguous, hence, not a valid identifier.
     */
    @Benchmark
    public void CacheBenchmark(Blackhole bh) {

        var reader = new CSVReader("res/students.txt");

        Cache<String, Student> usefulCache = new Cache<String, Student>(){
            private Map<String, Student> cache = new HashMap<String, Student>();

            @Override
            public boolean contains(String key){
                return cache.containsKey(key);
            }

            @Override
            public void put(String key, Student value) {
                cache.put(key, value);
            }

            @Override
            public Student get(String key){
                return cache.get(key);
            }

            @Override
            public void clear(){
                cache = new HashMap<String, Student>();
            }
        };

        for(int i=1;i<1000;i++){
            List<Student> result = new ArrayList<>();
            final List<Student> intermediate = new ArrayList<>();

            try (Stream<String> stream = Files.lines(Paths.get("res/students.txt"))) {
                stream.skip(1).limit(10).forEach(line -> {
                    if (!line.isEmpty()) {
                        String[] values = line.split("\t");
                        if(usefulCache.contains(values[1])) {
                            intermediate.add(usefulCache.get(values[1]));
                        }
                    }
                });
            } catch (IOException e){
                e.printStackTrace();
            }

            if (intermediate.size() < 10){
                result = reader.read(10);

                for(Student student : result){
                    usefulCache.put(student.getGivenName(), student);
                }
            }
            else {
                result = intermediate;
            }

            bh.consume(result);
        }
    }

    @Benchmark
    public void noChacheBenchmark(Blackhole bh) {

        var reader = new CSVReader("res/students.txt");

        Cache<String, Student> uselessCache = new Cache<String, Student>(){

            @Override
            public boolean contains(String key){
                return false;
            }

            @Override
            public void put(String key, Student value) {
            }

            @Override
            public Student get(String key){
                return null;
            }

            @Override
            public void clear(){
            }
        };

        for(int i=1;i<1000;i++){
            List<Student> result = new ArrayList<>();
            final List<Student> intermediate = new ArrayList();

            try (Stream<String> stream = Files.lines(Paths.get("res/students.txt"))) {
                stream.skip(1).limit(10).forEach(line -> {
                    if (!line.isEmpty()) {
                        String[] values = line.split("\t");
                        if(uselessCache.contains(values[1])) {
                            intermediate.add(uselessCache.get(values[1]));
                        }
                    }
                });
            } catch (IOException e){
                e.printStackTrace();
            }

            if (result.size() < 10){
                result = reader.read(10);

                for(Student student : result){
                    uselessCache.put(student.getGivenName(), student);
                }
            }

            bh.consume(result);
        }
    }

    @Benchmark
    public void sequentialStreamBenchmark(Blackhole bh){
        List<Student> studentList = new ArrayList<Student>();
        CSVReader reader = new CSVReader("res/students.txt");

        for (int i=0; i<1000; i++) {
            for(Student student : reader.read(1000)){
                studentList.add(student);
            }
        }

        bh.consume(studentList.stream().map(Student::getEmail).collect(Collectors.toList()));
    }

    @Benchmark
    public void parallelStreamBenchmark(Blackhole bh){
        List<Student> studentList = new ArrayList<Student>();
        CSVReader reader = new CSVReader("res/students.txt");

        for (int i=0; i<1000; i++) {
            for(Student student : reader.read(1000)){
                studentList.add(student);
            }
        }

        bh.consume(studentList.stream().parallel().map(Student::getEmail).collect(Collectors.toList()));
    }

    @Benchmark
    public void linkedListNoIteratorBenchmark(Blackhole bh) {
        List<Student> studentList = new LinkedList<Student>();
        CSVReader reader = new CSVReader("res/students.txt");

        studentList = reader.read(1000);

        for(int i=0;i<1000;i++){
            bh.consume(studentList.get(i));
        }
    }

    @Benchmark
    public void linkedListIteratorBenchmark(Blackhole bh) {
        List<Student> studentList = new LinkedList<Student>();
        CSVReader reader = new CSVReader("res/students.txt");

        studentList = reader.read(1000);

        for(Student student : studentList){
            bh.consume(student);
        }
    }

    @Benchmark
    public void arrayListNoIteratorBenchmark(Blackhole bh) {
        List<Student> studentList = new ArrayList<Student>();
        CSVReader reader = new CSVReader("res/students.txt");

        studentList = reader.read(1000);

        for(int i=0;i<1000;i++){
            bh.consume(studentList.get(i));
        }
    }

    @Benchmark
    public void arrayListIteratorBenchmark(Blackhole bh) {
        List<Student> studentList = new ArrayList<Student>();
        CSVReader reader = new CSVReader("res/students.txt");

        studentList = reader.read(1000);

        for(Student student : studentList){
            bh.consume(student);
        }
    }

    @Benchmark
    public void setBenchmark(Blackhole bh) {
        CSVReader reader = new CSVReader("res/students.txt");
        Set<Student> studentSet = new HashSet<Student>(reader.read(1000));

        for(int i=0;1<1000;i++){
            bh.consume(studentSet.contains(new Student("Bruce", "Diaz", "BruceEDiaz@superrito.com", "Enges")));
        }
    }

    @Benchmark
    public void listBenchmark(Blackhole bh) {
        CSVReader reader = new CSVReader("res/students.txt");
        List<Student> studentList = new LinkedList<Student>(reader.read(1000));

        for(int i=0;1<1000;i++){
            bh.consume(studentList.contains(new Student("Bruce", "Diaz", "BruceEDiaz@superrito.com", "Enges")));
        }
    }
}