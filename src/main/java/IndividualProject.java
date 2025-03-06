import bot.Bot;
import io.UI;
import task.TaskList;

// TODO: Writing doesn't work
// TODO: Show deleted task after deletion
// TODO: Show updated task after marking and unmarking

public class IndividualProject {
    private static final String PROJECT_DIRECTORY = System.getProperty("user.dir");
    private static final String DATA_PATH_STRING = PROJECT_DIRECTORY + "/data/" + "tasks.csv";

    public static void main(String[] args) {
        UI ui = new UI();
        TaskList tasksFromStorage = ui.readFromStorage(DATA_PATH_STRING);

        Bot bot = new Bot(tasksFromStorage);
        ui.connect(bot);
        ui.writeToStorage(bot.getTasks(), DATA_PATH_STRING);
    }
}