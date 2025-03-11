package command.add;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import task.Event;
import ui.InputReader;

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
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws DateTimeParseException If date strings are not formatted properly.
     */
    @Override
    public Event getAddedTask() throws IllegalArgumentException, DateTimeParseException {
        String[] requiredArgs = { "/from", "/to" };
        HashMap<String, String> argMap = InputReader.retriveArgMap(this.args, requiredArgs);

        String description = argMap.get("");
        String startDateString = argMap.get("/from");
        String endDateString = argMap.get("/to");
        Event event = new Event(description, startDateString, endDateString);

        return event;
    }

}