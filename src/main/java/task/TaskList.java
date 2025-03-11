package task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> list;

    private TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Returns a copy of the given TaskList.
     *
     * @param tasks The TaskList to copy.
     * @return The copied TaskList.
     */
    public static TaskList copyOf(TaskList tasks) {
        return new TaskList(new ArrayList<>(tasks.list));
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        if (this.list.isEmpty()) {
            return "No tasks present.";
        }

        return String.join("\n", IntStream.range(0, this.list.size())
                .mapToObj(i -> (i + 1) + ". " + this.list.get(i)).toList());
    }

    /**
     * Returns the raw string representation of the task list.
     *
     * @return The raw string representation of the task list.
     */
    public String getRawString() {
        return String.join("\n", this.list.stream()
                .map(task -> task.getRawString())
                .toList());
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
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

    /**
     * Returns the task at the specified index.
     *
     * @param oneBasedIndex The one-based index of the task.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task get(int oneBasedIndex) throws IndexOutOfBoundsException {
        return this.validateAndMapTask(oneBasedIndex, (index, task) -> task);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param oneBasedIndex The one-based index of the task to be deleted.
     * @param successAction The action to be performed after deletion.
     * @param <T> The type of the result.
     * @return The result of the success action.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public <T> T delete(int oneBasedIndex, Function<Task, T> successAction)
            throws IndexOutOfBoundsException {
        return validateAndMapTask(oneBasedIndex, (index, task) -> {
            this.list.remove(task);
            return successAction.apply(task);
        });
    }

    /**
     * Marks a task as complete.
     *
     * @param oneBasedIndex The one-based index of the task to be marked.
     * @param successAction The action to be performed after marking.
     * @param <T> The type of the result.
     * @return The result of the success action.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public <T> T mark(int oneBasedIndex, Function<Task, T> successAction)
            throws IndexOutOfBoundsException {
        return validateAndMapTask(oneBasedIndex, (index, task) -> {
            Task updatedTask = task.markAsComplete();
            this.list.set(index, updatedTask);

            return successAction.apply(updatedTask);
        });
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param oneBasedIndex The one-based index of the task to be unmarked.
     * @param successAction The action to be performed after unmarking.
     * @param <T> The type of the result.
     * @return The result of the success action.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public <T> T unmark(int oneBasedIndex, Function<Task, T> successAction)
            throws IndexOutOfBoundsException {
        return validateAndMapTask(oneBasedIndex, (index, task) -> {
            Task updatedTask = task.markAsIncomplete();
            this.list.set(index, updatedTask);

            return successAction.apply(updatedTask);
        });
    }

    public TaskList filter(String keyword) {
        List<Task> filteredTasks = this.list.parallelStream()
            .filter(task -> task.getDescription().contains(keyword))
            .toList();

        return new TaskList(new ArrayList<>(filteredTasks));
    }

}