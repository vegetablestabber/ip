package task;

import java.util.StringJoiner;

import storage.TaskReader;

public class Event extends Task {

    private final String startDateTime;
    private final String endDateTime;

    public static final String LINE_ID = "E";

    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    private Event(Task event, String startDateTime, String endDateTime) {
        super(event);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime
            + " to: " + endDateTime + ")";
    }

    @Override
    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(LINE_ID);
        sj.add(super.getRawString());
        sj.add(startDateTime);
        sj.add(endDateTime);

        return sj.toString();
    }

    @Override
    public Event markAsComplete() {
        return new Event(super.markAsComplete(), this.startDateTime, this.endDateTime);
    }

    @Override
    public Event markAsIncomplete() {
        return new Event(super.markAsIncomplete(), this.startDateTime, this.endDateTime);
    }

}