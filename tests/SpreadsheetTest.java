import spreadsheet.*;
import java.nio.file.*;

public class SpreadsheetTest {
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    public static void main(String[] args) throws Exception {
        Spreadsheet s = new Spreadsheet();
        s.setContent("A1", "10");
        s.setContent("B1", "Hello");
        s.setContent("C1", "=A1+2");
        s.setContent("AA7", "=SUMA(A1;3)");
        check(s.getTypedContent("A1") instanceof NumberContent, "numeric type");
        check(s.getTypedContent("B1") instanceof TextContent, "text type");
        check(s.getTypedContent("C1") instanceof FormulaContent, "formula type");
        check(Position.fromAddress("AA7").address().equals("AA7"), "multi-letter column");
        check(s.getContent("D1").isEmpty(), "empty cell");
        Path temp = Files.createTempFile("spreadsheet-test-", ".s2v");
        try {
            S2VStorage.save(s, temp);
            String file = Files.readString(temp);
            check(file.contains("=SUMA(A1,3)"), "S2V formula argument conversion");
            Spreadsheet loaded = S2VStorage.load(temp);
            check(loaded.getContent("A1").equals("10"), "round trip number");
            check(loaded.getContent("B1").equals("Hello"), "round trip text");
            check(loaded.getContent("C1").equals("=A1+2"), "round trip formula");
            check(loaded.getContent("AA7").equals("=SUMA(A1;3)"), "round trip function arguments");
            loaded.setContent("B1", "");
            check(loaded.getContent("B1").isEmpty(), "clear cell");
            System.out.println("PASS: 10 checks (types, addresses, empty, S2V, round trip, clear)");
        } finally { Files.deleteIfExists(temp); }
    }
}
