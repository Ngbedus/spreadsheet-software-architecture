package spreadsheet;

import java.nio.file.Path;
import java.util.Scanner;

/** Text interface kept separate from domain and persistence classes. */
public final class Main {
    public static void main(String[] args) {
        Spreadsheet sheet = new Spreadsheet();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1 Set content | 2 View content | 3 Save S2V | 4 Load S2V | 5 Exit");
                System.out.print("Choose: ");
                if (!scanner.hasNextLine()) return;
                String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> {
                            System.out.print("Cell (e.g. A1): ");
                            String cell = scanner.nextLine().trim();
                            System.out.print("Content (blank clears cell): ");
                            sheet.setContent(cell, scanner.nextLine());
                            System.out.println("Saved content in " + cell.toUpperCase());
                        }
                        case "2" -> {
                            System.out.print("Cell: ");
                            String cell = scanner.nextLine().trim();
                            System.out.println(cell.toUpperCase() + " content = [" + sheet.getContent(cell) + "]");
                        }
                        case "3" -> {
                            System.out.print("File path: ");
                            Path path = Path.of(scanner.nextLine().trim());
                            S2VStorage.save(sheet, path);
                            System.out.println("Spreadsheet saved: " + path);
                        }
                        case "4" -> {
                            System.out.print("File path: ");
                            Path path = Path.of(scanner.nextLine().trim());
                            sheet = S2VStorage.load(path);
                            System.out.println("Spreadsheet loaded: " + path);
                        }
                        case "5" -> { return; }
                        default -> System.out.println("Choose 1-5.");
                    }
                } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
            }
        }
    }
}
