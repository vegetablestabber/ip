package task;

import java.util.StringJoiner;

import storage.TaskReader;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    private final String dueByDateTime;

    public static final String LINE_ID = "D";

    /**
     * Constructs a Deadline with the specified description and due date/time.
     *
     * @param description The description of the task.
     * @param dueByDateTime The due date/time of the deadline.
     */
    public Deadline(String description, String dueByDateTime) {
        super(description);
        this.dueByDateTime = dueByDateTime;
    }

    private Deadline(Task deadline, String dueByDateTime) {
        super(deadline);
        this.dueByDateTime = dueByDateTime;
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
        sj.add(dueByDateTime);

        return sj.toString();
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueByDateTime + ")";
    }

    /**
     * Marks the deadline task as complete.
     *
     * @return The updated deadline task.
     */
    @Override
    public Deadline markAsComplete() {
        return new Deadline(super.markAsComplete(), this.dueByDateTime);
    }

    /**
     * Marks the deadline task as incomplete.
     *
     * @return The updated deadline task.
     */
    @Override
    public Deadline markAsIncomplete() {
        return new Deadline(super.markAsIncomplete(), this.dueByDateTime);
    }

}