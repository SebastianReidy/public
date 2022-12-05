import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.DoubleBuffer;
import java.sql.Array;
import java.util.*;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        compute();
    }

    public static List<Double> compute() throws FileNotFoundException {
        List<Double> numbers = readFile(new File("data"));

        double mean = computeMean(numbers);
        double std = computeStd(numbers, mean);

        List<Double> normalized = normalize(numbers, mean, std);

        System.out.println(normalized);

        writeFile(normalized, "output");

        return normalized;
    }

    private static List<Double> readFile(File file) throws FileNotFoundException{
        Scanner scanner = new Scanner(file);
        List<Double> numbers = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            double number = scanner.nextDouble();
            numbers.add(number);
        }

        scanner.close();

        return numbers;
    }

    private static double computeMean(List<Double> numbers) {
        double sum = 0;
        for (double f : numbers) {
            sum += f;
        }
        double mean = sum / numbers.size();

        return mean;
    }

    private static double computeStd(List<Double> numbers, double mean){
        double sumSquare = 0;
        for (double f : numbers) {
            double diff = f - mean;
            sumSquare += diff * diff;
        }
        double std = Math.sqrt(sumSquare / numbers.size());

        return std;
    }

    private static List<Double> normalize(List<Double> numbers, double mean, double std) {
        List<Double> normalized = new ArrayList<Double>();

        for (double f : numbers) {
            normalized.add((f - mean) / std);
        }
        return normalized;
    }

    private static void writeFile(List<Double> normalized, String fileName){
        try {
            FileWriter fw = new FileWriter(fileName);
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
