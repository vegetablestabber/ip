package command;

import error.MissingArgumentException;
import task.TaskList;

public class UnmarkCommand extends ModifyCommand {
    public static final String CLI_REPRESENTATION = "unmark";

    public UnmarkCommand(String[] args, TaskList tasks) {
        super(args, tasks);
    }

    @Override
    public String getOutput() throws MissingArgumentException, NumberFormatException {
        return "Updated: " + this.getTask();
    }
}