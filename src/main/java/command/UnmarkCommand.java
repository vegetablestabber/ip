package command;

import error.MissingArgumentException;
import task.Task;
import task.TaskList;

public class UnmarkCommand extends ModifyCommand {

    public static final String CLI_REPRESENTATION = "unmark";

    public UnmarkCommand(String[] args, TaskList tasks) throws MissingArgumentException,
    IndexOutOfBoundsException, NumberFormatException {
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
    public String getOutput() throws MissingArgumentException, NumberFormatException {
        return "Updated: " + this.getTask();
    }

}