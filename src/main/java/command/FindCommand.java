package command;

import error.MissingArgumentException;
import io.LineReader;
import task.TaskList;

public class FindCommand extends Command {

    private final TaskList tasks;
    private final String keyword;

    public static final String CLI_REPRESENTATION = "find";

    public FindCommand(String[] args, TaskList tasks) throws MissingArgumentException {
        super(args);
        this.tasks = tasks;
        this.keyword = LineReader.retriveArgValue(args);
    }

    @Override
    public String getOutput() {
        return this.tasks.filter(this.keyword).toString();
    }

}
