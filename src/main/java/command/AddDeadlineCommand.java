package command;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import io.LineReader;
import task.Deadline;

public class AddDeadlineCommand extends AddCommand {
    public static final String CLI_REPRESENTATION = "deadline";

    public AddDeadlineCommand(String[] args) {
        super(args);
    }

    @Override
    public Deadline getAddedTask() throws IllegalArgumentException, DateTimeParseException {
        String[] requiredArgs = { "/by" };
        HashMap<String, String> argMap = LineReader.retriveArgMap(this.args, requiredArgs);

        String description = argMap.get("");
        String dueDateString = argMap.get("/by");
        Deadline deadline = new Deadline(description, dueDateString);

        return deadline;
    }
}