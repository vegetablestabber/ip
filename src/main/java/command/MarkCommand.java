package command;

import task.Task;
import task.TaskList;

public class MarkCommand extends ModifyCommand {

    public static final String CLI_REPRESENTATION = "mark";

    public MarkCommand(String[] args, TaskList tasks)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        super(args, tasks);
    }

    private MarkCommand(MarkCommand command, Task task) {
        super(command, task);
    }

    @Override
    public MarkCommand updateTask(Task task) {
        return new MarkCommand(this, task);
    }

    @Override
    public String getOutput() throws IllegalArgumentException {
        return "Updated: " + this.getTask();
    }

}