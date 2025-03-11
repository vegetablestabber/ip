package storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import io.UI;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

public class TaskReader {

    public static final String DELIMITER = "|";

    private static Optional<Task> readTaskFromLine(String line) {
        String[] args = line.split(DELIMITER);
        String taskType = args[0];
        boolean isComplete = Boolean.parseBoolean(args[1]);
        String description = args[2];

        Optional<Task> task = Optional.empty();

        switch (taskType) {
        case ToDo.LINE_ID:
            task = Optional.of(new ToDo(description));
            break;
        case Deadline.LINE_ID:
            String dueByDateTime = args[3];
            task = Optional.of(new Deadline(description, dueByDateTime));
            break;
        case Event.LINE_ID:
            String startDueTime = args[3];
            String endDateTime = args[4];
            task = Optional.of(new Event(description, startDueTime, endDateTime));
            break;
        }

        task = task.map(t -> isComplete
                ? t.markAsComplete()
                : t.markAsIncomplete());

        return task;
    }

    public static TaskList read(String dataPathString, UI ui) {
        BufferedReader reader;
        TaskList tasks = new TaskList();

        try {
            Path dataPath = Paths.get(dataPathString);
            Files.createDirectories(dataPath);

            reader = Files.newBufferedReader(dataPath);
            boolean isEmpty = !reader.lines().anyMatch(line -> true);

            if (isEmpty) {
                reader.close();
                ui.printReadInitialisation();
                return new TaskList();
            }

            reader.lines().forEach(line -> readTaskFromLine(line).ifPresent(t -> tasks.add(t)));
            reader.close();

            ui.printReadSuccess(tasks.size());
        } catch (IOException e) {
            ui.printReadFailure(e);
            return new TaskList();
        }

        return tasks;
    }

}
