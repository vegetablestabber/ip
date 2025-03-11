package command.general;

import command.Command;
import task.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    private final TaskList tasks;

    public static final String CLI_REPRESENTATION = "list";

    /**
     * Constructs a ListCommand with the specified arguments and task list.
     *
     * @param args The arguments for the command.
     * @param tasks The list of tasks.
     */
    public ListCommand(String[] args, TaskList tasks) {
        super(args);
        this.tasks = tasks;
    }

    /**
     * Returns the output message for the list command.
     *
     * @return The output message.
     */
    @Override
    public String getOutput() {
        return this.tasks.toString();
    }

}
