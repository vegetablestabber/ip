import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class TaskList {

    private final ArrayList<Task> list;

    public TaskList(int taskCount) {
        this.list = new ArrayList<>(taskCount);
    }

    @Override
    public String toString() {
        return this.list.isEmpty()
            ? "No tasks present."
            : String.join("\n", IntStream.range(0, this.list.size())
                .mapToObj(i -> (i + 1) + ". " + this.list.get(i)).toList());
    }

    public int size() {
        return this.list.size();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    private void validateTaskBasedAction(int oneBasedIndex, BiConsumer<Integer, Task> successAction)
            throws Exception {
        int index = oneBasedIndex - 1;

        if (index >= 0 && index < this.list.size()) {
            Task task = this.list.get(index);
            successAction.accept(index, task);
        } else {
            String errorMessage = this.list.isEmpty()
                ? "You have no tasks."
                : "Index " + oneBasedIndex + " lies outside of valid range (1-"
                    + this.list.size() + ").";

            throw new Exception(errorMessage);
        }
    }

    public void delete(int oneBasedIndex, Consumer<Task> successAction) throws Exception {
        validateTaskBasedAction(oneBasedIndex, (index, task) -> {
            this.list.remove(task);
            successAction.accept(task);
        });
    }

    public void markTask(int oneBasedIndex, Consumer<Task> successAction) throws Exception {
        validateTaskBasedAction(oneBasedIndex, (index, task) -> {
            this.list.set(index, task.markAsComplete());
            successAction.accept(this.list.get(index));
        });
    }

    public void unmarkTask(int oneBasedIndex, Consumer<Task> successAction) throws Exception {
        validateTaskBasedAction(oneBasedIndex, (index, task) -> {
            this.list.set(index, task.markAsIncomplete());
            successAction.accept(this.list.get(index));
        });
    }

}