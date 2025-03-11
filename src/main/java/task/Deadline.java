package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

import storage.TaskReader;
import storage.TaskWriter;

public class Deadline extends Task {

    private final LocalDate dueDate;

    public static final String LINE_ID = "D";

    public Deadline(String description, String dueDateString) throws DateTimeParseException {
        super(description);
        this.dueDate = LocalDate.parse(dueDateString);
    }

    private Deadline(Task deadline, LocalDate dueDate) {
        super(deadline);
        this.dueDate = dueDate;
    }

    @Override
    public String getRawString() {
        StringJoiner sj = new StringJoiner(TaskReader.DELIMITER);
        sj.add(LINE_ID);
        sj.add(super.getRawString());
        sj.add(TaskWriter.formatDate(dueDate));

        return sj.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    @Override
    public Deadline markAsComplete() {
        return new Deadline(super.markAsComplete(), this.dueDate);
    }

    @Override
    public Deadline markAsIncomplete() {
        return new Deadline(super.markAsIncomplete(), this.dueDate);
    }

}