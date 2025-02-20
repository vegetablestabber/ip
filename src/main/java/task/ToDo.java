package task;

import java.util.StringJoiner;

import io.DataManager;

public class ToDo extends Task {

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
        StringJoiner sj = new StringJoiner(DataManager.DELIMITER);
        sj.add("T");
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