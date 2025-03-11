package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Pattern;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.UI;

/**
 * Reads tasks from a file.
 */
public class TaskReader {

    public static final String DELIMITER = "|";

    /**
     * Reads a task from a line of text.
     *
     * @param line The line of text.
     * @return An Optional containing the task if it was successfully read, or an empty Optional if not.
     */
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

    /**
     * Reads the tasks from the specified file.
     *
     * @param dataPathString The path to the file.
     * @return The list of tasks.
     */
    public static TaskList read(String dataPathString) {
        UI.printReadInitialisation();

        try {
            Path dataPath = Paths.get(dataPathString);
            Files.createDirectories(dataPath.getParent());

            if (Files.notExists(dataPath)) {
                UI.printDidNotRead();
                return new TaskList();
            }

            if (isFileEmpty(dataPathString)) {
                UI.printFileReadIsEmpty();
                return new TaskList();
            }

            return parseTasks(dataPathString);
        } catch (IOException e) {
            UI.printReadFailure(e);
            return new TaskList();
        }
    }

    /**
     * Checks if the file is empty.
     *
     * @param dataPathString The path to the file.
     * @return True if the file is empty, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    private static boolean isFileEmpty(String dataPathString) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataPathString));
        boolean isEmpty = reader.readLine() == null;
        reader.close();

        return isEmpty;
    }

    /**
     * Parses tasks from the file.
     *
     * @param dataPathString The path to the file.
     * @return The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    private static TaskList parseTasks(String dataPathString) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataPathString));
        TaskList tasks = new TaskList();

        reader.lines().forEach(line -> {
            Optional<Task> task = readTaskFromLine(line);
            task.ifPresent(t -> tasks.add(t));
        });

        reader.close();
        UI.printReadSuccess(tasks.size());

        return tasks;
    }

}
