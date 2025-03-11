package command.modify;

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
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     */
    public MarkCommand(String[] args, TaskList tasks)
        throws IllegalArgumentException, IndexOutOfBoundsException {
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

}