package command;

import java.util.Optional;

import error.MissingArgumentException;
import io.LineReader;
import task.Task;
import task.TaskList;

/**
 * Represents a command to modify a task.
 */
public abstract class ModifyCommand extends Command {

    private final TaskList tasks;
    private final int taskIndex;
    private final Optional<Task> taskToModify;

    private ModifyCommand(String[] args, TaskList tasks, int taskIndex, Optional<Task> taskToModify) {
        super(args);
        this.tasks = tasks;
        this.taskIndex = taskIndex;
        this.taskToModify = taskToModify;
    }

    /**
     * Constructs a ModifyCommand with the specified arguments and task list.
     *
     * @param args The arguments for the command.
     * @param tasks The list of tasks.
     * @throws MissingArgumentException If the task number is missing.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     * @throws NumberFormatException If the task number is not a valid number.
     */
    protected ModifyCommand(String[] args, TaskList tasks) throws MissingArgumentException,
        IndexOutOfBoundsException, NumberFormatException {
        this(args, tasks, LineReader.retriveIntArg(args), Optional.empty());
    }

    /**
     * Constructs a ModifyCommand with the specified command and task.
     *
     * @param command The command to be modified.
     * @param task The task to be modified.
     */
    protected ModifyCommand(ModifyCommand command, Task task) {
        this(command.args, command.tasks, command.taskIndex, Optional.of(task));
    }

    /**
     * Returns the index of the task to be modified.
     *
     * @return The index of the task to be modified.
     */
    public int getTaskIndex() {
        return this.taskIndex;
    }

    /**
     * Returns the task to be modified.
     *
     * @return The task to be modified.
     */
    public Task getTask() {
        return this.taskToModify.orElseGet(() -> this.tasks.get(this.taskIndex));
    }

    /**
     * Updates the task after modification.
     *
     * @param task The task to be updated.
     * @return The updated ModifyCommand.
     */
    public abstract ModifyCommand updateTask(Task task);

}