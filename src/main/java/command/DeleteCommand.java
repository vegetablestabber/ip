package command;

import error.MissingArgumentException;
import task.Task;
import task.TaskList;

public class DeleteCommand extends ModifyCommand {
    public static final String CLI_REPRESENTATION = "delete";

    public DeleteCommand(String[] args, TaskList tasks) throws MissingArgumentException,
    IndexOutOfBoundsException, NumberFormatException {
        super(args, tasks);
    }

    private DeleteCommand(DeleteCommand command, Task task) {
        super(command, task);
    }

    @Override
    public DeleteCommand updateTask(Task task) {
        return new DeleteCommand(this, task);
    }

    @Override
    public String getOutput() throws MissingArgumentException, NumberFormatException {
        return "Deleted: " + this.getTask();
    }
}