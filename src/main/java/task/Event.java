package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

import storage.TaskReader;
import storage.TaskWriter;

public class Event extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public static final String LINE_ID = "E";

    public Event(String description, String startDateString, String endDateString) throws DateTimeParseException {
        super(description);
        this.startDate = LocalDate.parse(startDateString);
        this.endDate = LocalDate.parse(endDateString);
    }

    private Event(Task event, LocalDate startDate, LocalDate endDate) {
        super(event);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate
            + " to: " + endDate + ")";
    }

    @Override
    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(LINE_ID);
        sj.add(super.getRawString());
        sj.add(TaskWriter.formatDate(startDate));
        sj.add(TaskWriter.formatDate(endDate));

        return sj.toString();
    }

    @Override
    public Event markAsComplete() {
        return new Event(super.markAsComplete(), this.startDate, this.endDate);
    }

    @Override
    public Event markAsIncomplete() {
        return new Event(super.markAsIncomplete(), this.startDate, this.endDate);
    }

}