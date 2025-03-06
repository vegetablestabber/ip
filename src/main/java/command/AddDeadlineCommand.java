package command;

import java.util.HashMap;

import error.AppException;
import io.LineReader;
import task.Deadline;

public class AddDeadlineCommand extends AddCommand {
    public static final String CLI_REPRESENTATION = "deadline";

    public AddDeadlineCommand(String[] args) {
        super(args);
    }

    @Override
    public Deadline getAddedTask() throws AppException {
        String[] requiredArgs = { "/by" };
        HashMap<String, String> argMap = LineReader.retriveArgMap(this.args, requiredArgs);

        String description = argMap.get("");
        String dueByDateTime = argMap.get("/by");
        Deadline deadline = new Deadline(description, dueByDateTime);

        return deadline;
    }
}