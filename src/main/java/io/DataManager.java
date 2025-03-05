package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

public class DataManager {
    private static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    private static final String PROJECT_DATA_DIRECTORY = PROJECT_DIRECTORY + "/data/";
    private static final String PROJECT_DATA_PATH = PROJECT_DATA_DIRECTORY + "tasks.csv";

    public static final String DELIMITER = "|";

    private static Optional<Task> readTaskFromLine(String line) {
        String[] args = line.split(DELIMITER);
        String taskType = args[0];
        boolean isComplete = Boolean.parseBoolean(args[1]);
        String description = args[2];

        Optional<Task> task = Optional.empty();

        switch (taskType) {
        case "T":
            task = Optional.of(new ToDo(description));
            break;
        case "D":
            String dueByDateTime = args[3];
            task = Optional.of(new Deadline(description, dueByDateTime));
            break;
        case "E":
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

    public static TaskList readData() throws IOException {
        TaskList tasks = new TaskList();

        Path dataDirectory = Paths.get(PROJECT_DATA_DIRECTORY);
        Files.createDirectories(dataDirectory);

        Path dataPath = Paths.get(PROJECT_DATA_PATH);
        BufferedReader reader = Files.newBufferedReader(dataPath);

        reader.lines()
            .forEach(line -> readTaskFromLine(line).ifPresent(t -> tasks.add(t)));

        reader.close();
        return tasks;
    }

    public static void writeData(TaskList tasks) throws IOException {
        Path dataDirectory = Paths.get(PROJECT_DATA_DIRECTORY);
        Files.createDirectories(dataDirectory);

        Path dataPath = Paths.get(PROJECT_DATA_PATH);
        Files.write(dataPath, tasks.getRawString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }
}
