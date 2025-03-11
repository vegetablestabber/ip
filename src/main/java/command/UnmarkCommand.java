package command;

import task.Task;
import task.TaskList;

public class UnmarkCommand extends ModifyCommand {

    public static final String CLI_REPRESENTATION = "unmark";

    public UnmarkCommand(String[] args, TaskList tasks)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        super(args, tasks);
    }

    private UnmarkCommand(UnmarkCommand command, Task task) {
        super(command, task);
    }

    @Override
    public UnmarkCommand updateTask(Task task) {
        return new UnmarkCommand(this, task);
    }

    @Override
    public String getOutput() throws IllegalArgumentException {
        return "Updated: " + this.getTask();
    }

}