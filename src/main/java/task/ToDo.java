package task;

import java.util.StringJoiner;

import storage.TaskReader;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    public static final String LINE_ID = "T";

    /**
     * Constructs a ToDo with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    private ToDo(Task todo) {
        super(todo);
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the raw string representation of the to-do task.
     *
     * @return The raw string representation of the to-do task.
     */
    @Override
    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(LINE_ID);
        sj.add(super.getRawString());

        return sj.toString();
    }

    /**
     * Marks the to-do task as complete.
     *
     * @return The updated to-do task.
     */
    @Override
    public ToDo markAsComplete() {
        return new ToDo(super.markAsComplete());
    }

    /**
     * Marks the to-do task as incomplete.
     *
     * @return The updated to-do task.
     */
    @Override
    public ToDo markAsIncomplete() {
        return new ToDo(super.markAsIncomplete());
    }

}