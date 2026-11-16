package spreadsheet;

public record NumberContent(String source) implements CellContent {
    public NumberContent {
        if (source == null || source.isBlank()) throw new IllegalArgumentException("Number cannot be blank");
        try {
            double number = Double.parseDouble(source);
            if (!Double.isFinite(number)) throw new NumberFormatException("not finite");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + source, e);
        }
    }
    @Override public String asText() { return source; }
    public double number() { return Double.parseDouble(source); }
}
