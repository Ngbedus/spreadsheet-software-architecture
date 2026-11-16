# Spreadsheet Project

## Software Architecture - Deliverable 2

### Project Description

This project implements the basic domain model of a spreadsheet application using Java and object-oriented programming principles.

The implementation supports storing numeric, text, and formula cell contents, as well as loading and saving spreadsheets using the S2V (Semicolon Separated Values) file format.

### Implemented Features

- Create and manage spreadsheet cells.
- Identify cells using addresses such as A1, B2, and AA1.
- Store numeric, text, and formula contents.
- Set, view, and clear cell contents.
- Save spreadsheet contents to S2V files.
- Load spreadsheet contents from S2V files.
- Interact with the spreadsheet using a console menu.

### Project Structure

- `Spreadsheet.java`; Manages spreadsheet cells.
- `Cell.java`: Represents a cell.
- `Position.java`: Represents cell coordinates.
- `CellContent.java`: Defines the cell content abstraction.
- `NumberContent.java`: Numeric cell content.
- `TextContent.java`: Text cell content.
- `FormulaContent.java`: Formula cell content.
- `S2VStorage.java`: Handles file loading and saving.
- `Main.java`: Console user interface.
- `SpreadsheetTest.java`: Basic automated checks.

### Requirements

Java Development Kit (JDK) 17 or newer.

### How to Run

1. Open the project in IntelliJ IDEA.
2. Run `Main.java`.
3. Use the console menu to set, view, load, and save spreadsheet contents.

### Testing

The project includes automated tests covering cell contents, cell addresses, and S2V file operations.

### Current Scope

This deliverable implements the domain classes and basic spreadsheet operation. Formula expressions are stored as cell contents but are not yet evaluated.

Formula evaluation, dependency management, functions, and ranges are outside the scope of this current implementation.
