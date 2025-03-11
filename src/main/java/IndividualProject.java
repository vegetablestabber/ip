import bot.Bot;
import storage.TaskReader;
import storage.TaskWriter;
import task.TaskList;
import ui.UI;

public class IndividualProject {

    private static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    private static final String DATA_PATH_STRING = PROJECT_DIRECTORY + "/data/" + "tasks.csv";

    public static void main(String[] args) {
        TaskList tasksFromStorage = TaskReader.read(DATA_PATH_STRING);

        Bot bot = new Bot(tasksFromStorage);
        UI.connect(bot);

        TaskWriter.write(bot.getTasks(), DATA_PATH_STRING);
    }

}