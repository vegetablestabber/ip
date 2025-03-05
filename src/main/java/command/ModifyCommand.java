package command;

import error.MissingArgumentException;
import io.ArgumentReader;
import task.Task;
import task.TaskList;

public abstract class ModifyCommand extends Command {
    public final TaskList tasks;

    public ModifyCommand(String[] args, TaskList tasks) {
        super(args);
        this.tasks = TaskList.copyOf(tasks);
    }

    public int getTaskIndex() throws MissingArgumentException,
        IndexOutOfBoundsException,NumberFormatException {
        return ArgumentReader.retriveIntArg(args);
    }

    public Task getTask() throws NumberFormatException,
        IndexOutOfBoundsException, MissingArgumentException {
        return this.tasks.get(getTaskIndex());
    }
}