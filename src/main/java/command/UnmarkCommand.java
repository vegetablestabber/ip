package command;

import task.Task;
import task.TaskList;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends ModifyCommand {

    public static final String CLI_REPRESENTATION = "unmark";

    /**
     * Constructs an UnmarkCommand with the specified arguments and task list.
     *
     * @param args The arguments for the command.
     * @param tasks The list of tasks.
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     */
    public UnmarkCommand(String[] args, TaskList tasks)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        super(args, tasks);
    }

    private UnmarkCommand(UnmarkCommand command, Task task) {
        super(command, task);
    }

    /**
     * Updates the task after unmarking it as not done.
     *
     * @param task The task to be updated.
     * @return The updated UnmarkCommand.
     */
    @Override
    public UnmarkCommand updateTask(Task task) {
        return new UnmarkCommand(this, task);
    }

}