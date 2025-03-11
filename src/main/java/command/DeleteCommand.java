package command;

import task.Task;
import task.TaskList;

public class DeleteCommand extends ModifyCommand {

    public static final String CLI_REPRESENTATION = "delete";

    public DeleteCommand(String[] args, TaskList tasks)
        throws IllegalArgumentException, IndexOutOfBoundsException {
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
    public String getOutput() throws IllegalArgumentException {
        return "Deleted: " + this.getTask();
    }

}