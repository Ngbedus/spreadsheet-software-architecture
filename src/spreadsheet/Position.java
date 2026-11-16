package spreadsheet;

import java.util.Locale;

/** Immutable zero-based internal position; addresses such as A1 are user-facing. */
public record Position(int row, int column) {
    public Position {
        if (row < 0 || column < 0) throw new IllegalArgumentException("Row/column must be nonnegative");
    }

    public static Position fromAddress(String address) {
        if (address == null || !address.trim().matches("(?i)[A-Z]+[1-9][0-9]*"))
            throw new IllegalArgumentException("Invalid cell address (example: A1): " + address);
        String normalized = address.trim().toUpperCase(Locale.ROOT);
        int i = 0;
        while (Character.isLetter(normalized.charAt(i))) i++;
        long col = 0;
        for (int j = 0; j < i; j++) {
            col = col * 26 + (normalized.charAt(j) - 'A' + 1);
            if (col > Integer.MAX_VALUE) throw new IllegalArgumentException("Column too large");
        }
        long rowNumber;
        try { rowNumber = Long.parseLong(normalized.substring(i)); }
        catch (NumberFormatException e) { throw new IllegalArgumentException("Row too large", e); }
        if (rowNumber > Integer.MAX_VALUE) throw new IllegalArgumentException("Row too large");
        return new Position((int)rowNumber - 1, (int)col - 1);
    }

    public String address() {
        long n = (long) column + 1;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        return sb + Integer.toString(row + 1);
    }
}
