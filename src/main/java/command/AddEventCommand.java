package command;

import java.util.HashMap;

import error.AppException;
import io.LineReader;
import task.Event;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends AddCommand {

    public static final String CLI_REPRESENTATION = "event";

    /**
     * Constructs an AddEventCommand with the specified arguments.
     *
     * @param args The arguments for the command.
     */
    public AddEventCommand(String[] args) {
        super(args);
    }

    /**
     * Returns the event task that was added.
     *
     * @return The added event task.
     * @throws AppException If there is an error getting the added task.
     */
    @Override
    public Event getAddedTask() throws AppException {
        String[] requiredArgs = { "/from", "/to" };
        HashMap<String, String> argMap = LineReader.retriveArgMap(this.args, requiredArgs);

        String description = argMap.get("");
        String startDateTime = argMap.get("/from");
        String endDateTime = argMap.get("/to");
        Event event = new Event(description, startDateTime, endDateTime);

        return event;
    }

}