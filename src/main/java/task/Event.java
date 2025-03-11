package task;

import java.util.StringJoiner;

import storage.TaskReader;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private final String startDateTime;
    private final String endDateTime;

    public static final String LINE_ID = "E";

    /**
     * Constructs an Event with the specified description, start date/time, and end date/time.
     *
     * @param description The description of the task.
     * @param startDateTime The start date/time of the event.
     * @param endDateTime The end date/time of the event.
     */
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

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime
            + " to: " + endDateTime + ")";
    }

    /**
     * Returns the raw string representation of the event task.
     *
     * @return The raw string representation of the event task.
     */
    @Override
    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(LINE_ID);
        sj.add(super.getRawString());
        sj.add(startDateTime);
        sj.add(endDateTime);

        return sj.toString();
    }

    /**
     * Marks the event task as complete.
     *
     * @return The updated event task.
     */
    @Override
    public Event markAsComplete() {
        return new Event(super.markAsComplete(), this.startDateTime, this.endDateTime);
    }

    /**
     * Marks the event task as incomplete.
     *
     * @return The updated event task.
     */
    @Override
    public Event markAsIncomplete() {
        return new Event(super.markAsIncomplete(), this.startDateTime, this.endDateTime);
    }

}