import java.io.BufferedWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            System.out.println("usage : -i <input> -o <output> [-v]");
            System.exit(1);
        }

        if (!args[0].equals("-i")) {
            System.out.println("missing input file");
            System.exit(1);
        }
        String inputFileName = args[1];

        if (!args[2].equals("-o")) {
            System.out.println("missing output file");
            System.exit(1);
        }
        String outputFileName = args[3];

        final Boolean verbose = args.length == 5 && args[4].equals("-v");

        sanitizeCsv(inputFileName, outputFileName, verbose);
    }

    public static void sanitizeCsv(String inputFileName, String outputFileName, boolean verbose) throws IOException {

        // from: https://javabeginners.de/Allgemeines/Logging/Einfaches_Logging.php
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);

        String[] regexes = {"^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$",
                "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$",
                "^.+$",
                "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$"};

        try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName));
            stream.forEach(line -> {

                if (!line.isEmpty()) {
                    String[] values = line.split(",");
                    boolean valid = true;
                    if (values.length != 4) {
                        valid = false;
                        if (verbose) {
                            logger.info(String.format("ignored {%s} : missing values", line));
                        }
                    } else {
                        for(int i=0;valid && i<values.length; i++) {
                            Pattern pattern = Pattern.compile(regexes[i]);
                            Matcher matcher = pattern.matcher(values[i]);
                            if(!matcher.matches()) {
                                valid = false;
                                if (verbose) {
                                    logger.info(String.format("ignored {%s} : invalid format", line));
                                }
                            }
                        }
                    }
                    if (valid) {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            writer.close();
        }
    }

    /*
    private static void checkLine(String line, BufferedWriter writer, boolean verbose) throws IOException {
         String datetime = "^\\d{4}-(0[1-9] | 11 | 12)-(0[1-9] | 1[0-9] | 2[0-9] | 30 | 31) " +
                "\\s (0[0-9] | 1[0-9] | 2[0-4]):[0-5][0-9]:[0-5][0-9]$";
         String IPAddress = "(([0-9] | [0-9][0-9] | 1[0-9][0-9] | 2[0-4][0-9] | 25[0-5]).){3}" +
                " ([0-9] | [0-9][0-9] | 1[0-9][0-9] | 2[0-4][0-9] | 25[0-5])";
         String userAgent = ".+";
         String url = "^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";

        String[] split = line.split("[,]", 0);

        boolean correctLine = true;
        for (String field : split) {
            correctLine = correctLine &&
                            (checkPattern(field, datetime) ||
                            checkPattern(field, IPAddress) ||
                            checkPattern(field, userAgent) ||
                            checkPattern(field, url));
        }

        if(correctLine)
            writer.write(line);
        else if (verbose) {
            System.out.println(line);
        }
    }

    private static boolean checkPattern(String line, String pattern) {
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(line);
        return matcher.matches();
    }

     */
}

