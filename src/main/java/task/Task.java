package task;

import java.util.StringJoiner;

import storage.TaskReader;

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

    public Task(String description) {
        this(description, false);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
            this.isComplete ? "X" : " ", this.description);
    }

    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(this.isComplete + "");
        sj.add(this.description);

        return sj.toString();
    }

    public Task markAsComplete() {
        return new Task(this.description, true);
    }

    public Task markAsIncomplete() {
        return new Task(this.description, false);
    }

}