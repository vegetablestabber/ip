package command;

import error.MissingArgumentException;
import task.TaskList;

public class DeleteCommand extends ModifyCommand {
    public static final String CLI_REPRESENTATION = "delete";

    public DeleteCommand(String[] args, TaskList tasks) {
        super(args, tasks);
    }

    @Override
    public String getOutput() throws MissingArgumentException, NumberFormatException {
        return "Deleted: " + this.getTask();
    }
}