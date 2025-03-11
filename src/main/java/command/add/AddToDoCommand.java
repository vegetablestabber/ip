package command.add;

import java.time.format.DateTimeParseException;

import task.ToDo;
import ui.InputReader;

/**
 * Represents a command to add a to-do task.
 */
public class AddToDoCommand extends AddCommand {

    public static final String CLI_REPRESENTATION = "todo";

    /**
     * Constructs an AddToDoCommand with the specified arguments.
     *
     * @param args The arguments for the command.
     */
    public AddToDoCommand(String[] args) {
        super(args);
    }

    /**
     * Returns the to-do task that was added.
     *
     * @return The added to-do task.
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws DateTimeParseException If date strings are not formatted properly.
     */
    @Override
    public ToDo getAddedTask() throws IllegalArgumentException {
        String description = InputReader.retriveStringArg(this.args);
        ToDo toDo = new ToDo(description);

        return toDo;
    }

}