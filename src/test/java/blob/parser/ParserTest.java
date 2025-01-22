package blob.parser;

import blob.command.AddCommand;
import blob.command.Command;
import blob.exception.BlobExceptions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    void parse_validTodoInput_returnsAddCommand() throws Exception {
        Parser parser = new Parser();
        String input = "todo read book";
        Command command = parser.parse(input);
        assertNotNull(command);
        assertTrue(command instanceof AddCommand);
    }

    @Test
    void parse_invalidInput_throwsUnknownCommandException() {
        Parser parser = new Parser();
        String input = "random invalid input";
        assertThrows(BlobExceptions.UnknownCommandException.class, () -> {
            parser.parse(input);
        });
    }
}
