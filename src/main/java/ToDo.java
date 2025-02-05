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
    public ToDo markAsComplete() {
        return new ToDo(super.markAsComplete());
    }

    @Override
    public ToDo markAsIncomplete() {
        return new ToDo(super.markAsIncomplete());
    }

}
