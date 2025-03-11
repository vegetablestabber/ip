package command;

import io.LineReader;
import task.ToDo;

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
     * @throws AppException If there is an error getting the added task.
     */
    @Override
    public ToDo getAddedTask() throws IllegalArgumentException {
        String description = LineReader.retriveArgValue(this.args);
        ToDo toDo = new ToDo(description);

        return toDo;
    }

}