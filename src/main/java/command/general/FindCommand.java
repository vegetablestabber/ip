package command.general;

import command.Command;
import task.TaskList;
import ui.InputReader;

/**
 * Represents a command to filter tasks by their descriptions containing a given keyword.
 */
public class FindCommand extends Command {

    private final TaskList tasks;
    private final String keyword;

    public static final String CLI_REPRESENTATION = "find";

    /**
     * Constructs a FindCommand with the specified arguments and task list.
     *
     * @param args The arguments for the command.
     * @param tasks The list of tasks.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    public FindCommand(String[] args, TaskList tasks) throws IllegalArgumentException {
        super(args);
        this.tasks = tasks;
        this.keyword = InputReader.retriveStringArg(args);
    }

    /**
     * Returns the output message for the find command.
     *
     * @return The output message.
     */
    @Override
    public String getOutput() {
        return this.tasks.filter(this.keyword).toString();
    }

}
