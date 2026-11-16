package spreadsheet;

/** Formula expression is preserved verbatim and NOT evaluated in Deliverable 2. */
public record FormulaContent(String expression) implements CellContent {
    public FormulaContent {
        if (expression == null || !expression.startsWith("="))
            throw new IllegalArgumentException("Formula must start with =");
    }
    @Override public String asText() { return expression; }
}
