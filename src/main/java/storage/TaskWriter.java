package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.UI;
import task.TaskList;

public class TaskWriter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static String getDateFormat() {
        return DATE_FORMAT;
    }

    public static String formatDate(LocalDate date) {
        return DATE_FORMATTER.format(date);
    }

    public static void write(TaskList tasks, String dataPathString, UI ui) {
        ui.printWriteInitialisation();

        try {
            Path dataDirectory = Paths.get(dataPathString);
            Files.createDirectories(dataDirectory.getParent());

            Path dataPath = Paths.get(dataPathString);
            Files.write(dataPath, tasks.getRawString().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            ui.printWriteSuccess(tasks.size());
        } catch (IOException e) {
            ui.printWriteFailure(e);
        }
    }

}
