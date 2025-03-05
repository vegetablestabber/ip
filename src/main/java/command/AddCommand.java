package command;

import error.AppException;
import task.Task;

public abstract class AddCommand extends Command {
    public AddCommand(String[] args) {
        super(args);
    }

    public abstract Task getAddedTask() throws AppException;

    @Override
    public String getOutput() throws AppException {
        return "Added: " + this.getAddedTask();
    }
}