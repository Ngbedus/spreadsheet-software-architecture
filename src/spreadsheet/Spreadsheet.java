package spreadsheet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** Sparse data model: empty cells are absent from the map. */
public final class Spreadsheet {
    private final Map<Position, Cell> cells = new HashMap<>();

    public void setContent(String address, String raw) {
        setContent(Position.fromAddress(address), raw);
    }
    public void setContent(Position position, String raw) {
        if (raw == null) throw new IllegalArgumentException("Content cannot be null");
        if (raw.isEmpty()) {
            cells.remove(position);
            return;
        }
        CellContent content;
        if (raw.startsWith("=")) content = new FormulaContent(raw);
        else if (looksLikeNumber(raw)) content = new NumberContent(raw);
        else content = new TextContent(raw);
        cells.put(position, new Cell(position, content));
    }
    private boolean looksLikeNumber(String raw) {
        try { return Double.isFinite(Double.parseDouble(raw)) && !raw.isBlank(); }
        catch (NumberFormatException e) { return false; }
    }
    public String getContent(String address) { return getContent(Position.fromAddress(address)); }
    public String getContent(Position position) {
        Cell cell = cells.get(position);
        return cell == null ? "" : cell.content().asText();
    }
    public CellContent getTypedContent(String address) {
        Cell cell = cells.get(Position.fromAddress(address));
        return cell == null ? null : cell.content();
    }
    public Map<Position, Cell> cells() { return Collections.unmodifiableMap(cells); }
    public int lastRowIndex() { return cells.keySet().stream().mapToInt(Position::row).max().orElse(-1); }
    public int lastColumnIndex() { return cells.keySet().stream().mapToInt(Position::column).max().orElse(-1); }
}
