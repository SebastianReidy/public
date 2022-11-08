package example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;

@Fork(value = 1, warmups = 0)
@Warmup(iterations = 1)
@Measurement(iterations = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class Bench {
    class UselessCache implements Cache<Integer, Student>{

        public UselessCache() {}
        @Override
        public boolean contains(Integer key){
            return false;
        }

        @Override
        public void put(Integer key, Student value){ }

        @Override
        public Student get(Integer key) {
            return null;
        }

        @Override
        public void clear() { }
    }

    class UsefullCache implements Cache<Integer, Student>{
        private HashMap<Integer, Student> storage;

        public UsefullCache() {
            this.storage = new HashMap<Integer, Student>();
        }

        @Override
        public boolean contains(Integer key){
            return storage.containsKey(key);
        }

        @Override
        public void put(Integer key, Student value){
            storage.put(key, value);
        }

        @Override
        public Student get(Integer key) {
            return storage.get(key);
        }

        @Override
        public void clear(){
            storage = new HashMap<Integer,Student>();
        }
    }

    @Benchmark
    public void BaselineReaderTenLines(Blackhole bh){
        var reader = new CSVReader("res/students.txt", new UselessCache());

        for(int i=0;i<5;i++) {
            bh.consume(reader.read(10));
        }
    }

    @Benchmark
    public void CacheReaderTenLines(Blackhole bh) {
        var reader = new CSVReader("res/students.txt", new UsefullCache());

        for(int i=0;i<5;i++) {
            bh.consume(reader.read(10));
        }
    }

    @Benchmark
    public void SingleStreamReadingEmails(Blackhole bh){
        var reader = new CSVReader("res/students.txt", new UsefullCache());

        for(int i=0;i<5;i++) {
            List<Student> students = reader.read(1000);
            bh.consume(students.stream().map(std -> std.getEmail()).collect(Collectors.toList()));
        }
    }

    @Benchmark
    public void ParallelStreamReadingEmails(Blackhole bh){
        var reader = new CSVReader("res/students.txt", new UsefullCache());
        for(int i=0;i<5;i++) {
            List<Student> students = reader.read(1000);
            bh.consume(students.parallelStream().map(std -> std.getEmail()).collect(Collectors.toList()));
        }
    }

    @Benchmark
    public void StoringReadsInSet(Blackhole bh){
        Set<Student> hashSet = new HashSet<Student>();
        hashSet.add(new Student("0","1","2","3"));  //SOLUTION: read real students and check if one of the real students is contained
        for(int i=0;i<1000;i++){
            bh.consume(hashSet.contains(new Student("0","1","2","3")));
        }
    }

    @Benchmark
    public void StoringReadsInList(Blackhole bh)  {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("0","1","2","3"));
        for(int i=0;i<0;i++){
            bh.consume(list.contains(new Student("0","1","2","3")));
        }
    }

    // The naive iteration over linked list is twice as slow as if we use an iterator or an array list
    // The iterator over the array list is as fast as the naive iteration over the array list
    @Benchmark
    public void GetFromLinkedList(Blackhole bh) {
        var reader = new CSVReader("res/students.txt", new UsefullCache());
        List<Student> list = new LinkedList<Student>(reader.read(1000));
        for(int i=0;i<1000;i++){
            bh.consume(list.get(i));
        }
    }

    @Benchmark
    public void GetFromLinkedListIterator(Blackhole bh){
        var reader = new CSVReader("res/students.txt", new UsefullCache());
        List<Student> list = new LinkedList<Student>(reader.read(1000));
        for(final Student std : list){
            bh.consume(std);
        }
    }

    @Benchmark
    public void GetFromArrayList(Blackhole bh) {
        var reader = new CSVReader("res/students.txt", new UsefullCache());
        List<Student> list = new ArrayList<Student>(reader.read(1000));
        for(int i=0;i<1000;i++){
            bh.consume(list.get(i));
        }
    }

    @Benchmark
    public void GetFromArrayListIterator(Blackhole bh){
        var reader = new CSVReader("res/students.txt", new UsefullCache());
        List<Student> list = new ArrayList<Student>(reader.read(1000));
        for(final Student std : list){
            bh.consume(std);
        }
    }
}