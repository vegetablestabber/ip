package command.add;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import task.Deadline;
import ui.InputReader;

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
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws DateTimeParseException If date strings are not formatted properly.
     */
    @Override
    public Deadline getAddedTask() throws IllegalArgumentException, DateTimeParseException {
        String[] requiredArgs = { "/by" };
        HashMap<String, String> argMap = InputReader.retriveArgMap(this.args, requiredArgs);

        String description = argMap.get("");
        String dueDateString = argMap.get("/by");
        Deadline deadline = new Deadline(description, dueDateString);

        return deadline;
    }
}