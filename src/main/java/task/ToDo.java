package task;

import java.util.StringJoiner;

import storage.TaskReader;

public class ToDo extends Task {

    public static final String LINE_ID = "T";

    public ToDo(String description) {
        super(description);
    }

    private ToDo(Task todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(LINE_ID);
        sj.add(super.getRawString());

        return sj.toString();
    }

    @Override
    public ToDo markAsComplete() {
        return new ToDo(super.markAsComplete());
    }

    @Override
    public ToDo markAsIncomplete() {
        return new ToDo(super.markAsIncomplete());
    }

}