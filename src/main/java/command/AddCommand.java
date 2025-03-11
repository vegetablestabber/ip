package command;

import error.AppException;
import task.Task;

/**
 * Represents a command to add a task.
 */
public abstract class AddCommand extends Command {

    /**
     * Constructs an AddCommand with the specified arguments.
     *
     * @param args The arguments for the command.
     */
    public AddCommand(String[] args) {
        super(args);
    }

    /**
     * Returns the task that was added.
     *
     * @return The added task.
     * @throws AppException If there is an error getting the added task.
     */
    public abstract Task getAddedTask() throws AppException;

    /**
     * Returns the output message for the add command.
     *
     * @return The output message.
     * @throws AppException If there is an error getting the output.
     */
    @Override
    public String getOutput() throws AppException {
        return "Added: " + this.getAddedTask();
    }
}