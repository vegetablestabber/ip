package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Pattern;

import io.UI;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

public class TaskReader {

    public static final String DELIMITER = "|";

    private static Optional<Task> readTaskFromLine(String line) {
        String[] args = line.split(Pattern.quote(DELIMITER));
        String taskType = args[0];
        boolean isComplete = Boolean.parseBoolean(args[1]);
        String description = args[2];

        Optional<Task> task = Optional.empty();

        switch (taskType) {
        case ToDo.LINE_ID:
            task = Optional.of(new ToDo(description));
            break;
        case Deadline.LINE_ID:
            String dueDateString = args[3];
            task = Optional.of(new Deadline(description, dueDateString));
            break;
        case Event.LINE_ID:
            String startDateString = args[3];
            String endDateString = args[4];

            task = Optional.of(new Event(description, startDateString, endDateString));
            break;
        }

        task = task.map(t -> isComplete
                ? t.markAsComplete()
                : t.markAsIncomplete());

        return task;
    }

    public static TaskList read(String dataPathString, UI ui) {
        ui.printReadInitialisation();

        BufferedReader reader;
        TaskList tasks = new TaskList();

        try {
            Path dataPath = Paths.get(dataPathString);
            Files.createDirectories(dataPath.getParent());

            if (Files.notExists(dataPath)) {
                ui.printDidNotRead();
                return new TaskList();
            }

            reader = new BufferedReader(new FileReader(dataPathString));
            boolean isEmpty = reader.readLine() == null;
            reader.close();

            if (isEmpty) {
                ui.printFileReadIsEmpty();
                return new TaskList();
            }

            reader = new BufferedReader(new FileReader(dataPathString));
            reader.lines().forEach(line -> {
                Optional<Task> task = readTaskFromLine(line);
                task.ifPresent(t -> tasks.add(t));
            });

            reader.close();
            ui.printReadSuccess(tasks.size());
        } catch (IOException e) {
            ui.printReadFailure(e);
            return new TaskList();
        }

        return tasks;
    }

}
