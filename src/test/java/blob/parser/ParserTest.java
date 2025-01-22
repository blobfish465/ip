package blob.parser;

import blob.command.AddCommand;
import blob.command.Command;
import blob.exception.BlobExceptions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Parser class.
 * These tests validate the functionality of the Parser class,
 * ensuring it correctly interprets and converts input strings into Command objects.
 */
class ParserTest {

    /**
     * Test if parsing a valid "todo" input string correctly returns an AddCommand object.
     * This test ensures that the parser can handle valid "todo" commands and
     * properly creates an AddCommand.
     *
     * @throws Exception if an error occurs during the parsing process.
     */
    @Test
    void parse_validTodoInput_returnsAddCommand() throws Exception {
        Parser parser = new Parser();
        String input = "todo read book";
        Command command = parser.parse(input);
        assertNotNull(command);
        assertTrue(command instanceof AddCommand);
    }

    /**
     * Test if parsing an invalid input string results in an UnknownCommandException.
     * This test checks the parser's ability to handle unrecognized commands
     * and throw the appropriate exception.
     */
    @Test
    void parse_invalidInput_throwsUnknownCommandException() {
        Parser parser = new Parser();
        String input = "random invalid input";
        assertThrows(BlobExceptions.UnknownCommandException.class, () -> {
            parser.parse(input);
        });
    }
}
