package spreadsheet;

public record TextContent(String text) implements CellContent {
    public TextContent {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
    }
    @Override public String asText() { return text; }
}
