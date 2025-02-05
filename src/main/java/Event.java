public class Event extends Task {

    private String startDateTime;
    private String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    private Event(Task event, String startDateTime, String endDateTime) {
        super(event);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime
            + " to: " + endDateTime + ")";
    }

    @Override
    public Event markAsComplete() {
        return new Event(super.markAsComplete(), this.startDateTime, this.endDateTime);
    }

    @Override
    public Event markAsIncomplete() {
        return new Event(super.markAsIncomplete(), this.startDateTime, this.endDateTime);
    }

}
