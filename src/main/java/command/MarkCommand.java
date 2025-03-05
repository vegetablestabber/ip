package command;

import error.MissingArgumentException;
import task.TaskList;

public class MarkCommand extends ModifyCommand {
    public static final String CLI_REPRESENTATION = "mark";

    public MarkCommand(String[] args, TaskList tasks) {
        super(args, tasks);
    }

    @Override
    public String getOutput() throws MissingArgumentException, NumberFormatException {
        return "Updated: " + this.getTask();
    }
}