package command;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import io.LineReader;
import task.Event;

public class AddEventCommand extends AddCommand {

    public static final String CLI_REPRESENTATION = "event";

    public AddEventCommand(String[] args) {
        super(args);
    }

    @Override
    public Event getAddedTask() throws IllegalArgumentException, DateTimeParseException {
        String[] requiredArgs = { "/from", "/to" };
        HashMap<String, String> argMap = LineReader.retriveArgMap(this.args, requiredArgs);

        String description = argMap.get("");
        String startDateString = argMap.get("/from");
        String endDateString = argMap.get("/to");
        Event event = new Event(description, startDateString, endDateString);

        return event;
    }

}