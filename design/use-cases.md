# Deliverable 1 - draft use-case descriptions

**System:** Text-based spreadsheet. **Primary actor:** Spreadsheet User.

These are draft use cases covering the **whole project**, including functionality not implemented in Deliverable 2. Review with your team and instructor's later prescribed command syntax.

## UC01 - Create a spreadsheet
**Goal:** Start working on an empty spreadsheet.
**Precondition:** The application is running.
**Main flow:** (1) User selects New. (2) System initializes an empty sheet. (3) System confirms it is ready.
**Postcondition:** An empty spreadsheet is active.

## UC02 - Set or edit cell content
**Goal:** Set text, number, or formula in a cell.
**Precondition:** A spreadsheet is active.
**Main flow:** (1) User specifies a cell address such as A1. (2) User enters content. (3) System validates the address and stores the content. (4) In full project, system validates formulas and updates dependent calculated values. (5) System confirms completion.
**Exceptions:** Invalid address; invalid formula; formula introduces circular dependency (full project).
**Postcondition:** Cell content is updated if valid.

## UC03 - Clear cell
**Goal:** Remove stored content from a cell.
**Main flow:** (1) User selects cell. (2) User clears content. (3) System removes cell content and, in the full version, updates dependent values.

## UC04 - View cell content
**Goal:** Inspect the literal content typed or loaded in a cell.
**Main flow:** (1) User specifies address. (2) System returns original number/text/formula text, not formula evaluation.
**Exceptions:** Invalid address.

## UC05 - View evaluated cell value
**Goal:** Inspect the computed value of a cell.
**Main flow:** (1) User specifies cell. (2) System computes formula dependencies when needed. (3) System shows value.
**Exceptions:** Syntax, circular-reference, or nonnumeric-operand errors.
**Implementation:** Later deliverable, not required for Deliverable 2.

## UC06 - Save spreadsheet
**Goal:** Persist active sheet in S2V text file.
**Main flow:** (1) User enters filename/path. (2) System serializes rows with `;` separators and formulas as content. (3) Inside formulas, `;` arguments are saved as `,`. (4) System confirms save.
**Exceptions:** Invalid path; write failure; unrepresentable text.

## UC07 - Load spreadsheet
**Goal:** Restore spreadsheet content from S2V file.
**Main flow:** (1) User provides path. (2) System reads file rows/columns. (3) System recreates cell contents and restores formula argument `,` to `;`. (4) Full implementation evaluates formulas after load.
**Exceptions:** Missing file; invalid file; read failure.

## UC08 - Evaluate formula
**Goal:** Evaluate formulas with arithmetic, parentheses, and cell references.
**Main flow:** (1) System parses formula into expression nodes. (2) System resolves references. (3) System checks cycles. (4) System evaluates and updates dependent values as appropriate.
**Exceptions:** Syntax error; circular dependency; value error.
**Implementation:** Later deliverable.

## UC09 - Evaluate functions and ranges
**Goal:** Evaluate SUMA, MIN, MAX and PROMEDIO over numbers, cells, ranges and nested functions.
**Main flow:** (1) System parses function and arguments. (2) Expands any ranges. (3) Evaluates arguments. (4) Applies function.
**Implementation:** Later deliverable.

## UC10 - Exit application
**Goal:** Stop session.
**Main flow:** (1) User selects exit. (2) Program ends. Unsaved changes are not automatically persisted.

## Review notes
- UC01/UC03/UC10 are reasonable UI design choices inferred from the required behavior, not explicitly mandated menu names.
- Instructor says a later common repertoire/syntax of menu commands will be imposed. Adapt menu accordingly.
- The full project calls for error handling, circular-dependency detection, recomputation, and functions; Deliverable 2 intentionally excludes evaluation.
