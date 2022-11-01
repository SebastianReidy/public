import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        compute();
    }

    public static List<Double> compute() throws FileNotFoundException {

        List<Double> numbers = readFile("data");

        List<Double> normalized = normalize(numbers); // SOLUTION: consider the computation of std and mean as two seperate tasks

        writeFile(normalized, "output");

        return normalized;
    }

    private static List<Double> readFile(String path) throws FileNotFoundException{
        File file = new File(path);

        Scanner scanner = new Scanner(file);
        List<Double> numbers = new ArrayList<>();

        while (scanner.hasNextDouble()) {
            double number = scanner.nextDouble();
            numbers.add(number);
        }
        scanner.close();

        return numbers;
    }

    private static List<Double> normalize(List<Double> numbers) {
        List<Double> normalized = new ArrayList<>();
        double sum = 0;
        for (double f : numbers) {
            sum += f;
        }
        double mean = sum / numbers.size();
        double sumSquare = 0;
        for (double f : numbers) {
            double diff = f - mean;
            sumSquare += diff * diff;
        }
        double std = Math.sqrt(sumSquare / numbers.size());
        for (double f : numbers) {
            normalized.add((f - mean) / std);
        }
        System.out.println(normalized);

        return normalized;
    }

    private static void writeFile(List<Double> normalized, String file){
        try {
            FileWriter fw = new FileWriter(file);
            for (double n : normalized) {
                fw.write(Double.toString(n));
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing output file");
        }
        System.out.println("Wrote output file.");
    }
}
