Java CLI application that reads HTML content from a URL, tracks tag depth using a stack-based approach, and returns the deepest text node. Detects malformed HTML and handles URL connection errors. Built for a technical challenge using only JDK 17.

## Requirements

- JDK 17

## Compilation

```bash
javac HtmlAnalyzer.java

## Execution
java HtmlAnalyzer <URL>

## example
java HtmlAnalyzer http://hiring.axreng.com/internship/example1.html
-------------------------------------------------------------------
## Expected Output
The program prints exactly one of the following:

The deepest text content found in the HTML structure

malformed HTML (if malformed detection is implemented)

URL connection error (if the HTML cannot be retrieved)
-----------------------------------------------------
## Approach

The solution processes the HTML document line by line and uses a Stack to track the current depth of the tag hierarchy.

For each line:

Opening tags increase depth

Closing tags decrease depth

Text lines are evaluated against the current maximum depth

If multiple text segments exist at the same deepest level, the first occurrence is returned
-------------------------------------------------------------------------------------------
## Assumptions (From Challenge Specification)

The solution assumes:

Each line contains only one of:

Opening tag

Closing tag

Text content

No attributes in opening tags

Only paired tags are used (no self-closing tags)

Indentation spaces should be ignored

Empty lines should be ignored
-------------------------------
## Malformed HTML Detection (Optional Bonus)

If implemented, malformed HTML is detected when:

Closing tags do not match the latest opened tag

There are unclosed tags at the end of processing
