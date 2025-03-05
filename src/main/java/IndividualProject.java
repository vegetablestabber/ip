import ui.Bot;
import ui.UI;

public class IndividualProject {
    public static void main(String[] args) {
        Bot bot = new Bot();
        UI ui = new UI(bot);

        ui.activate();
    }
}