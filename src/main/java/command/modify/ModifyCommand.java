package command.modify;

import java.util.Optional;

import command.Command;
import task.Task;
import task.TaskList;
import ui.InputReader;

/**
 * Represents a command to modify a task.
 */
public abstract class ModifyCommand extends Command {

    private final TaskList tasks;
    private final int taskIndex;
    private final Optional<Task> taskToModify;

    /**
     * Constructs a ModifyCommand with the specified arguments, task list, task index, and task to modify.
     *
     * @param args The arguments for the command.
     * @param tasks The list of tasks.
     * @param taskIndex The index of the task to be modified.
     * @param taskToModify The task to be modified.
     */
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
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     */
    protected ModifyCommand(String[] args, TaskList tasks)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        this(args, tasks, InputReader.retriveIntArg(args), Optional.empty());
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
     * Returns the output message for the unmark command.
     *
     * @return The output message.
     */
    @Override
    public String getOutput() {
        return "Updated: " + this.getTask();
    }

    /**
     * Updates the task after modification.
     *
     * @param task The task to be updated.
     * @return The updated ModifyCommand.
     */
    public abstract ModifyCommand updateTask(Task task);

}