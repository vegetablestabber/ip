package command.modify;

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
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     */
    public DeleteCommand(String[] args, TaskList tasks)
        throws IllegalArgumentException, IndexOutOfBoundsException {
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
     */
    @Override
    public String getOutput() {
        return "Deleted: " + this.getTask();
    }

}