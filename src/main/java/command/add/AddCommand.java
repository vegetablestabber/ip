package command.add;

import command.Command;
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
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    public abstract Task getAddedTask() throws IllegalArgumentException;

    /**
     * Returns the output message for the add command.
     *
     * @return The output message.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    @Override
    public String getOutput() throws IllegalArgumentException {
        return "Added: " + this.getAddedTask();
    }
}