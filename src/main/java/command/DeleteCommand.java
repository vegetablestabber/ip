package command;

import error.MissingArgumentException;
import task.Task;
import task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends ModifyCommand {

    public static final String CLI_REPRESENTATION = "delete";

    /**
     * Constructs a DeleteCommand with the specified arguments and task list.
     *
     * @param args The arguments for the command.
     * @param tasks The list of tasks.
     * @throws MissingArgumentException If the task number is missing.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     * @throws NumberFormatException If the task number is not a valid number.
     */
    public DeleteCommand(String[] args, TaskList tasks) throws MissingArgumentException,
    IndexOutOfBoundsException, NumberFormatException {
        super(args, tasks);
    }

    private DeleteCommand(DeleteCommand command, Task task) {
        super(command, task);
    }

    /**
     * Updates the task after deletion.
     *
     * @param task The task to be updated.
     * @return The updated DeleteCommand.
     */
    @Override
    public DeleteCommand updateTask(Task task) {
        return new DeleteCommand(this, task);
    }

    /**
     * Returns the output message for the delete command.
     *
     * @return The output message.
     * @throws MissingArgumentException If the task number is missing.
     * @throws NumberFormatException If the task number is not a valid number.
     */
    @Override
    public String getOutput() throws MissingArgumentException, NumberFormatException {
        return "Deleted: " + this.getTask();
    }

}