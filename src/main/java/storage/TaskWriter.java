package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

import task.TaskList;

public class TaskWriter {
    public static void writeData(TaskList tasks, String dataPathString,
            Consumer<Integer> successAction, Runnable failureAction) {
        try {
            Path dataDirectory = Paths.get(dataPathString);
            Files.createDirectories(dataDirectory);

            Path dataPath = Paths.get(dataPathString);
            Files.write(dataPath, tasks.getRawString().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            successAction.accept(tasks.size());
        } catch (IOException e) {
            System.out.println(e);
            failureAction.run();
        }
    }
}
