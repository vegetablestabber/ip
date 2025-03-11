package command;

import task.Task;

public abstract class AddCommand extends Command {
    public AddCommand(String[] args) {
        super(args);
    }

    public abstract Task getAddedTask() throws IllegalArgumentException;

    @Override
    public String getOutput() throws IllegalArgumentException {
        return "Added: " + this.getAddedTask();
    }
}