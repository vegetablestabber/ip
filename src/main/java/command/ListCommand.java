package command;

import task.TaskList;

public class ListCommand extends Command {
    private final TaskList tasks;

    public static final String CLI_REPRESENTATION = "list";

    public ListCommand(String[] args, TaskList tasks) {
        super(args);
        this.tasks = tasks;
    }

    @Override
    public String getOutput() {
        return this.tasks.toString();
    }
}
