package stringsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringSearch {

    public static void main(String[] args) {
        // Check if arguments are provided
        if (args.length < 2) {
            System.out.println("Usage: search <pattern> <file>");
            return;
        }

        // Extract the search pattern and file name from arguments
        String pattern = args[0];
        String fileName = args[1];

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
     * Searches for the given pattern in the specified file and returns matching
     * lines.
     *
     * @param pattern The string to search for.
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
