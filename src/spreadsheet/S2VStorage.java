package spreadsheet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Basic S2V: cells separated by semicolons, rows by newlines. */
public final class S2VStorage {
    private S2VStorage() { }

    public static void save(Spreadsheet spreadsheet, Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        int finalRow = spreadsheet.lastRowIndex();
        for (int row = 0; row <= finalRow; row++) {
            int finalColumnThisRow = -1;
            for (Position pos : spreadsheet.cells().keySet())
                if (pos.row() == row) finalColumnThisRow = Math.max(finalColumnThisRow, pos.column());
            List<String> parts = new ArrayList<>();
            for (int column = 0; column <= finalColumnThisRow; column++) {
                String raw = spreadsheet.getContent(new Position(row, column));
                if (raw.startsWith("=")) raw = raw.replace(';', ','); // assignment's S2V formula rule
                else if (raw.indexOf(';') >= 0 || raw.indexOf('\n') >= 0 || raw.indexOf('\r') >= 0)
                    throw new IOException("S2V cannot represent semicolons/newlines inside plain text (row " + (row + 1) + ")");
                parts.add(raw);
            }
            lines.add(String.join(";", parts));
        }
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static Spreadsheet load(Path path) throws IOException {
        Spreadsheet sheet = new Spreadsheet();
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (int row = 0; row < lines.size(); row++) {
            String[] parts = lines.get(row).split(";", -1);
            for (int col = 0; col < parts.length; col++) {
                String content = parts[col];
                if (content.startsWith("=")) content = content.replace(',', ';');
                sheet.setContent(new Position(row, col), content);
            }
        }
        return sheet;
    }
}
