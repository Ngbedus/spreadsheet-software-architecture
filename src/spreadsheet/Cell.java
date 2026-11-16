package spreadsheet;

public record Cell(Position position, CellContent content) {
    public Cell {
        if (position == null || content == null) throw new IllegalArgumentException("Missing cell position/content");
    }
}
