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
        return String.join("\n", IntStream.range(0, this.list.size())
                .mapToObj(i -> (i + 1) + ". " + this.list.get(i)).toList());
    }

    public int size() {
        return this.list.size();
    }

    public void add(String description) {
        this.list.add(new Task(description));
    }

    private void validateTaskBasedAction(int oneBasedIndex, BiConsumer<Integer, Task> action)
            throws Exception {
        int index = oneBasedIndex - 1;

        if (index >= 0 && index < this.list.size()) {
            Task task = this.list.get(index);
            action.accept(index, task);
        } else {
            throw new Exception("Index " + oneBasedIndex + "lies outside of valid range (1-"
                    + this.list.size() + ").");
        }
    }

    public void markTask(int oneBasedIndex, Consumer<Task> action) throws Exception {
        validateTaskBasedAction(oneBasedIndex, (index, task) -> {
            this.list.set(index, task.markAsComplete());
            action.accept(this.list.get(index));
        });
    }

    public void unmarkTask(int oneBasedIndex, Consumer<Task> action) throws Exception {
        validateTaskBasedAction(oneBasedIndex, (index, task) -> {
            this.list.set(index, task.markAsIncomplete());
            action.accept(this.list.get(index));
        });
    }
}
