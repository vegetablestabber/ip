package ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import io.CommandReader;
import io.DataManager;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

public class Skeets {

    private final TaskList tasks;

    public static final String LOGO = "     ______ ____________________\n" + "    / __/ //_/ __/ __/_  __/ __/\n"
            + "   _\\ \\/ ,< / _// _/  / / _\\ \\  \n" + "  /___/_/|_/___/___/ /_/ /___/  ";

    public Skeets() {
        TaskList tasks = new TaskList();

        try {
            tasks = DataManager.readData();
        } catch (IOException e) {

        }

        this.tasks = tasks;
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

        try {
            DataManager.writeData(this.tasks);
        } catch (IOException e) {

        }
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
            case "delete":
                deleteTask(givenArgs);
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

    private void addToDo(String[] givenArgs) throws Exception {
        String description = CommandReader.retriveArgValue(givenArgs);
        addTask(new ToDo(description));
    }

    private void addDeadline(String[] givenArgs) throws Exception {
        String[] requiredArgs = { "/by" };
        HashMap<String, String> argMap = CommandReader.retriveArgMap(givenArgs, requiredArgs);

        String description = argMap.get("");
        String dueByDateTime = argMap.get("/by");

        addTask(new Deadline(description, dueByDateTime));
    }

    private void addEvent(String[] givenArgs) throws Exception {
        String[] requiredArgs = { "/from", "/to" };
        HashMap<String, String> argMap = CommandReader.retriveArgMap(givenArgs, requiredArgs);

        String description = argMap.get("");
        String startDateTime = argMap.get("/from");
        String endDateTime = argMap.get("/to");

        addTask(new Event(description, startDateTime, endDateTime));
    }

    private void deleteTask(String[] givenArgs) throws Exception {
        int oneBasedIndex = CommandReader.retriveIntArg(givenArgs);
        this.tasks.delete(oneBasedIndex, task -> System.out.println("Deleted: " + task));
    }

    private void markTask(String[] givenArgs) throws Exception {
        if (givenArgs.length != 2) {
            throw new Exception("Does not meet the required number of arguments (actual: "
                    + givenArgs.length + ", expected: 2).");
        }

        int oneBasedIndex = Integer.parseInt(givenArgs[1]);
        this.tasks.markTask(oneBasedIndex, task -> System.out.println("Updated: " + task));
    }

    private void unmarkTask(String[] givenArgs) throws Exception {
        if (givenArgs.length != 2) {
            throw new Exception("Not enough arguments (actual: " + givenArgs.length + ", expected: 2).");
        }

        int oneBasedIndex = Integer.parseInt(givenArgs[1]);
        this.tasks.unmarkTask(oneBasedIndex, task -> System.out.println("Updated: " + task));
    }

}