package spreadsheet;

/** A cell's stored content, NOT its calculated value. */
public sealed interface CellContent permits NumberContent, TextContent, FormulaContent {
    String asText();
}
