import java.util.HashMap;
import java.util.Scanner;

public class Skeets {

    private final TaskList tasks;

    public static final String LOGO = "     ______ ____________________\n" + "    / __/ //_/ __/ __/_  __/ __/\n"
            + "   _\\ \\/ ,< / _// _/  / / _\\ \\  \n" + "  /___/_/|_/___/___/ /_/ /___/  ";

    public Skeets(int taskCount) {
        this.tasks = new TaskList(taskCount);
    }

    public void activate(Scanner scanner) {
        greetUser();
        String input;

        do {
            input = requestUserInput(scanner);

            try {
                handleUserInput(input);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!isUserExiting(input));

        dismissUser();
    }

    private void greetUser() {
        System.out.println(LOGO + "\n\nBend your knee and ask what you desire.");
    }

    private void dismissUser() {
        System.out.print("\nYou are dismissed.");
    }

    private String requestUserInput(Scanner scanner) {
        System.out.print("\nWhat do you want? ");
        return scanner.nextLine().strip();
    }

    private void handleUserInput(String input) throws Exception {
        String[] givenArgs = input.split(" ");

        if (!isUserExiting(input)) {
            String command = givenArgs[0];

            switch (command) {
            case "todo":
                addToDo(givenArgs);
                break;
            case "deadline":
                addDeadline(givenArgs);
                break;
            case "event":
                addEvent(givenArgs);
                break;
            case "list":
                listTasks();
                break;
            case "mark":
                markTask(givenArgs);
                break;
            case "unmark":
                unmarkTask(givenArgs);
                break;
            default:
                throw new Exception("Input '" + input + "' is invalid.");
            }
        }
    }

    private boolean isUserExiting(String input) {
        return input.isEmpty() || input.equals("bye");
    }

    private void listTasks() {
        System.out.println(this.tasks);
    }

    private void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Added: " + task);
    }

    private void addToDo(String[] givenArgs) {
        String description = ConsoleTools.retriveArgValue(givenArgs);
        addTask(new ToDo(description));
    }

    private void addDeadline(String[] givenArgs) throws Exception {
        String[] requiredArgs = {"/by"};
        HashMap<String, String> argMap = ConsoleTools.retriveArgMap(givenArgs, requiredArgs);

        String description = argMap.get("");
        String dueByDateTime = argMap.get("/by");

        addTask(new Deadline(description, dueByDateTime));
    }

    private void addEvent(String[] givenArgs) throws Exception {
        String[] requiredArgs = {"/from", "/to"};
        HashMap<String, String> argMap = ConsoleTools.retriveArgMap(givenArgs, requiredArgs);

        String description = argMap.get("");
        String startDateTime = argMap.get("/from");
        String endDateTime = argMap.get("/to");

        addTask(new Event(description, startDateTime, endDateTime));
    }

    private void markTask(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Not enough arguments (actual: " + args.length + ", expected: 2).");
        }

        int oneBasedIndex = Integer.parseInt(args[1]);
        this.tasks.markTask(oneBasedIndex, task -> System.out.println("Updated: " + task));
    }

    private void unmarkTask(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Not enough arguments (actual: " + args.length + ", expected: 2).");
        }

        int oneBasedIndex = Integer.parseInt(args[1]);
        this.tasks.unmarkTask(oneBasedIndex, task -> System.out.println("Updated: " + task));
    }

}
