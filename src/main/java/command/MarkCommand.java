package command;

import error.MissingArgumentException;
import task.Task;
import task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends ModifyCommand {

    public static final String CLI_REPRESENTATION = "mark";

    /**
     * Constructs a MarkCommand with the specified arguments and task list.
     *
     * @param args The arguments for the command.
     * @param tasks The list of tasks.
     * @throws MissingArgumentException If the task number is missing.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     * @throws NumberFormatException If the task number is not a valid number.
     */
    public MarkCommand(String[] args, TaskList tasks) throws MissingArgumentException,
    IndexOutOfBoundsException, NumberFormatException {
        super(args, tasks);
    }

    private MarkCommand(MarkCommand command, Task task) {
        super(command, task);
    }

    /**
     * Updates the task after marking it as done.
     *
     * @param task The task to be updated.
     * @return The updated MarkCommand.
     */
    @Override
    public MarkCommand updateTask(Task task) {
        return new MarkCommand(this, task);
    }

    /**
     * Returns the output message for the mark command.
     *
     * @return The output message.
     * @throws MissingArgumentException If the task number is missing.
     * @throws NumberFormatException If the task number is not a valid number.
     */
    @Override
    public String getOutput() throws MissingArgumentException, NumberFormatException {
        return "Updated: " + this.getTask();
    }

}