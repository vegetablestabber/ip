package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

import storage.TaskReader;
import storage.TaskWriter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public static final String LINE_ID = "E";

    /**
     * Constructs an Event with the specified description, start date/time, and end date/time.
     *
     * @param description The description of the task.
     * @param startDateString The start date of the event.
     * @param endDateString The end date of the event.
     * @throws DateTimeParseException If date strings are not formatted properly.
     */
    public Event(String description, String startDateString, String endDateString) throws DateTimeParseException {
        super(description);
        this.startDate = LocalDate.parse(startDateString);
        this.endDate = LocalDate.parse(endDateString);
    }

    /**
     * Constructs an Event from an existing task and dates.
     *
     * @param event The existing task.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    private Event(Task event, LocalDate startDate, LocalDate endDate) {
        super(event);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate
            + " to: " + endDate + ")";
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
        sj.add(TaskWriter.formatDate(startDate));
        sj.add(TaskWriter.formatDate(endDate));

        return sj.toString();
    }

    /**
     * Marks the event task as complete.
     *
     * @return The updated event task.
     */
    @Override
    public Event markAsComplete() {
        return new Event(super.markAsComplete(), this.startDate, this.endDate);
    }

    /**
     * Marks the event task as incomplete.
     *
     * @return The updated event task.
     */
    @Override
    public Event markAsIncomplete() {
        return new Event(super.markAsIncomplete(), this.startDate, this.endDate);
    }

}