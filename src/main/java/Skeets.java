import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

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
                System.out.println("Error: " + e);
            }
        } while (!isUserExiting(input));

        dismissUser();
        scanner.close();
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
        String[] args = input.split("\s");

        if (!isUserExiting(input)) {
            String command = args[0];

            switch (command) {
            case "list":
                listTasks();
                break;
            case "mark":
                markTask(args);
                break;
            case "unmark":
                unmarkTask(args);
                break;
            default:
                addTask(input);
                break;
            }
        }
    }

    private boolean isUserExiting(String input) {
        return input.isEmpty() || input.equals("bye");
    }

    private void listTasks() {
        System.out.println(this.tasks);
    }

    private void addTask(String description) {
        this.tasks.add(description);
        System.out.println("Task added: " + description);
    }

    private void markTask(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Not enough arguments (actual: " + args.length + ", expected: 2).");
        }

        int oneBasedIndex = Integer.parseInt(args[1]);

        this.tasks.markTask(oneBasedIndex, task -> System.out
                .println(String.format("'%s' has been marked as complete.", task)));
    }

    private void unmarkTask(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Not enough arguments (actual: " + args.length + ", expected: 2).");
        }

        int oneBasedIndex = Integer.parseInt(args[1]);

        this.tasks.unmarkTask(oneBasedIndex, task -> System.out
                .println(String.format("'%s' has been marked as incomplete.", task)));
    }
}
