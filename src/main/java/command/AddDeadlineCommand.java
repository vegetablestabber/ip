package command;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import io.LineReader;
import task.Deadline;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String CLI_REPRESENTATION = "deadline";

    /**
     * Constructs an AddDeadlineCommand with the specified arguments.
     *
     * @param args The arguments for the command.
     */
    public AddDeadlineCommand(String[] args) {
        super(args);
    }

    /**
     * Returns the deadline task that was added.
     *
     * @return The added deadline task.
     * @throws AppException If there is an error getting the added task.
     */
    @Override
    public Deadline getAddedTask() throws IllegalArgumentException, DateTimeParseException {
        String[] requiredArgs = { "/by" };
        HashMap<String, String> argMap = LineReader.retriveArgMap(this.args, requiredArgs);

        String description = argMap.get("");
        String dueDateString = argMap.get("/by");
        Deadline deadline = new Deadline(description, dueDateString);

        return deadline;
    }
}