package task;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> list;

    private TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public static TaskList copyOf(TaskList tasks) {
        return new TaskList(new ArrayList<>(tasks.list));
    }

    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "No tasks present.";
        }

        return String.join("\n", IntStream.range(0, this.list.size())
                .mapToObj(i -> (i + 1) + ". " + this.list.get(i)).toList());
    }

    public String getRawString() {
        return String.join("\n", this.list.stream()
                .map(task -> task.getRawString())
                .toList());
    }

    public int size() {
        return this.list.size();
    }

    private <T> T validateAndMapTask(int oneBasedIndex, BiFunction<Integer, Task, T> successAction)
            throws IllegalStateException, IndexOutOfBoundsException {
        int index = oneBasedIndex - 1;

        if (index >= 0 && index < this.list.size()) {
            Task task = this.list.get(index);
            return successAction.apply(index, task);
        }

        if (this.list.isEmpty()) {
            throw new IllegalStateException("You have no tasks.");
        }

        throw new IndexOutOfBoundsException("Index " + oneBasedIndex +
                " lies outside of valid range (1-" + this.list.size() + ").");
    }

    public Task get(int oneBasedIndex) throws IndexOutOfBoundsException {
        return this.validateAndMapTask(oneBasedIndex, (index, task) -> task);
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public <T> T delete(int oneBasedIndex, Supplier<T> successSupplier)
            throws IndexOutOfBoundsException {
        return validateAndMapTask(oneBasedIndex, (index, task) -> {
            this.list.remove(task);
            return successSupplier.get();
        });
    }

    public <T> T mark(int oneBasedIndex, Supplier<T> successSupplier)
            throws IndexOutOfBoundsException {
        return validateAndMapTask(oneBasedIndex, (index, task) -> {
            this.list.set(index, task.markAsComplete());
            return successSupplier.get();
        });
    }

    public <T> T unmark(int oneBasedIndex, Supplier<T> successSupplier)
            throws IndexOutOfBoundsException {
        return validateAndMapTask(oneBasedIndex, (index, task) -> {
            this.list.set(index, task.markAsIncomplete());
            return successSupplier.get();
        });
    }

}