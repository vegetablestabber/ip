import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Skeets {
    private final ArrayList<String> tasks;

    public static final String LOGO = "     ______ ____________________\n"
        + "    / __/ //_/ __/ __/_  __/ __/\n"
        + "   _\\ \\/ ,< / _// _/  / / _\\ \\  \n"
        + "  /___/_/|_/___/___/ /_/ /___/  ";

    public Skeets(int taskCount) {
        this.tasks = new ArrayList<>(taskCount);
    }

    public void activate(Scanner scanner) {
        greetUser();
        String input;

        do {
            input = requestUserInput(scanner);
            handleUserInput(input);
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

    private void handleUserInput(String input) {
        String[] args = input.split("\s");
        
        if (!isUserExiting(input)) {
            String command = args[0];

            switch (command) {
                case "list":
                    listTasks();
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
        IntStream.range(0, this.tasks.size())
            .forEach(i -> System.out.println((i + 1) + ". " + this.tasks.get(i)));
    }

    private void addTask(String description) {
        this.tasks.add(description);
        System.out.println("Task added: " + description);
    }
}