package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import io.UI;
import task.TaskList;

public class TaskWriter {
    public static void write(TaskList tasks, String dataPathString, UI ui) {
        ui.printWriteInitialisation();

        try {
            Path dataDirectory = Paths.get(dataPathString);
            Files.createDirectories(dataDirectory);

            Path dataPath = Paths.get(dataPathString);
            Files.write(dataPath, tasks.getRawString().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            ui.printWriteSuccess(tasks.size());
        } catch (IOException e) {
            ui.printWriteFailure(e);
        }
    }
}
