package command;

import java.util.HashMap;

import error.AppException;
import io.ArgumentReader;
import task.Event;

public class AddEventCommand extends AddCommand {
    public static final String CLI_REPRESENTATION = "event";

    public AddEventCommand(String[] args) {
        super(args);
    }

    @Override
    public Event getAddedTask() throws AppException {
        String[] requiredArgs = { "/from", "/to" };
        HashMap<String, String> argMap = ArgumentReader.retriveArgMap(this.args, requiredArgs);

        String description = argMap.get("");
        String startDateTime = argMap.get("/from");
        String endDateTime = argMap.get("/to");
        Event event = new Event(description, startDateTime, endDateTime);

        return event;
    }
}