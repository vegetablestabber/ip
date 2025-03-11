package command;

import java.util.Optional;

import io.LineReader;
import task.Task;
import task.TaskList;

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

    protected ModifyCommand(String[] args, TaskList tasks)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        this(args, tasks, LineReader.retriveIntArg(args), Optional.empty());
    }

    protected ModifyCommand(ModifyCommand command, Task task) {
        this(command.args, command.tasks, command.taskIndex, Optional.of(task));
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }

    public Task getTask() {
        return this.taskToModify.orElseGet(() -> this.tasks.get(this.taskIndex));
    }

    public abstract ModifyCommand updateTask(Task task);

}