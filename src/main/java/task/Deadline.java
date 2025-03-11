package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

import storage.TaskReader;
import storage.TaskWriter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private final LocalDate dueDate;

    public static final String LINE_ID = "D";

    /**
     * Constructs a Deadline with the specified description and due date.
     *
     * @param description The description of the task.
     * @param dueDateString The due date of the deadline.
     * @throws DateTimeParseException If date strings are not formatted properly.
     */
    public Deadline(String description, String dueDateString) throws DateTimeParseException {
        super(description);
        this.dueDate = LocalDate.parse(dueDateString);
    }

    private Deadline(Task deadline, LocalDate dueDate) {
        super(deadline);
        this.dueDate = dueDate;
    }

    /**
     * Returns the raw string representation of the deadline task.
     *
     * @return The raw string representation of the deadline task.
     */
    @Override
    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(LINE_ID);
        sj.add(super.getRawString());
        sj.add(TaskWriter.formatDate(dueDate));

        return sj.toString();
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Marks the deadline task as complete.
     *
     * @return The updated deadline task.
     */
    @Override
    public Deadline markAsComplete() {
        return new Deadline(super.markAsComplete(), this.dueDate);
    }

    /**
     * Marks the deadline task as incomplete.
     *
     * @return The updated deadline task.
     */
    @Override
    public Deadline markAsIncomplete() {
        return new Deadline(super.markAsIncomplete(), this.dueDate);
    }

}