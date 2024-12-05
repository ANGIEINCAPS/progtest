package stringsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringSearch {
    static String pattern;
    static String fileName;

    public static void main(String[] args) {
        checkInput(args);

        try {
            // Read the file and search for the pattern
            List<String> matchingLines = searchInFile(pattern, fileName);

            // Print the matching lines
            if (matchingLines.isEmpty()) {
                System.out.println("No matches found.");
            } else {
                System.out.println("Matching lines:");
                for (String line : matchingLines) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Validates the input arguments and assigns values to the pattern and file
     * name.
     * <p>
     * This method checks if the required arguments (search pattern and file name)
     * are provided. If not, it prints a usage message and exits the program. If the
     * arguments are valid, the pattern and file name are assigned to the
     * corresponding
     * static variables for further processing.
     * </p>
     *
     * @param args An array of command-line arguments:
     *             - args[0]: The search pattern to look for.
     *             - args[1]: The file name to search within.
     * 
     *             Note: This method exits early without performing further
     *             operations if the
     *             arguments are insufficient.
     */
    private static void checkInput(String[] args) {
        // Check if arguments are provided and have correct usage.
        if (args.length < 2) {
            System.out.println("Usage: search <pattern> <file>");
            return;
        }

        pattern = args[0];
        fileName = args[1];
    }

    /**
     * Searches for the given pattern in the specified file and returns matching
     * lines.
     *
     * @param pattern  The string to search for.
     * @param fileName The name of the file to search in.
     * @return A list of lines containing the pattern.
     * @throws IOException If an I/O error occurs.
     */
    private static List<String> searchInFile(String pattern, String fileName) throws IOException {
        List<String> matchingLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(pattern)) {
                    matchingLines.add(line);
                }
            }
        }
        return matchingLines;
    }
}
