public class Deadline extends Task {
    private String dueByDateTime;

    public Deadline(String description, String dueByDateTime) {
        super(description);
        this.dueByDateTime = dueByDateTime;
    }

    private Deadline(Task deadline, String dueByDateTime) {
        super(deadline);
        this.dueByDateTime = dueByDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueByDateTime + ")";
    }

    @Override
    public Deadline markAsComplete() {
        return new Deadline(super.markAsComplete(), this.dueByDateTime);
    }

    @Override
    public Deadline markAsIncomplete() {
        return new Deadline(super.markAsIncomplete(), this.dueByDateTime);
    }
}
