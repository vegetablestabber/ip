package task;

import java.util.StringJoiner;

import storage.TaskReader;

/**
 * Represents a task.
 */
public class Task {

    private final String description;
    private final boolean isComplete;

    private Task(String description, boolean isComplete) {
        this.description = description;
        this.isComplete = isComplete;
    }

    protected Task(Task task) {
        this(task.description, task.isComplete);
    }

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
            this.isComplete ? "X" : " ", this.description);
    }

    /**
     * Returns the raw string representation of the task.
     *
     * @return The raw string representation of the task.
     */
    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(this.isComplete + "");
        sj.add(this.description);

        return sj.toString();
    }

    /**
     * Marks the task as complete.
     *
     * @return The updated task.
     */
    public Task markAsComplete() {
        return new Task(this.description, true);
    }

    /**
     * Marks the task as incomplete.
     *
     * @return The updated task.
     */
    public Task markAsIncomplete() {
        return new Task(this.description, false);
    }

}