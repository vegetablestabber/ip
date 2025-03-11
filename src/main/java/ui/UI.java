package ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import bot.Bot;
import command.Command;
import command.general.ExitCommand;
import storage.TaskWriter;

/**
 * Represents the user interface for interacting with the bot.
 */
public class UI {

    /** The logo displayed when starting the application. */
    private static final String LOGO = "     ______ ____________________\n" + "    / __/ //_/ __/ __/_  __/ __/\n"
            + "   _\\ \\/ ,< / _// _/  / / _\\ \\  \n" + "  /___/_/|_/___/___/ /_/ /___/  ";

    /**
     * Connects the UI to the bot and starts the interaction loop.
     *
     * @param bot The bot to connect to.
     */
    public static void connect(Bot bot) {
        greetUser();
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            input = requestUserInput(scanner);

            try {
                Command command = bot.handleUserInput(input);
                System.out.println(command.getOutput());
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Error: '" + e.getParsedString()
                    + "' is not of the format '" + TaskWriter.DATE_FORMAT + "'.");
            }
        } while (!isUserExiting(input));

        scanner.close();
    }

    /**
     * Displays the welcome message and logo to the user.
     */
    private static void greetUser() {
        System.out.println(LOGO + "\n\nBend your knee and ask what you desire.");
    }

    /**
     * Requests and retrieves input from the user.
     *
     * @param scanner The scanner to read input from.
     * @return The user's input as a string.
     */
    private static String requestUserInput(Scanner scanner) {
        System.out.print("\nWhat do you want? ");
        return scanner.nextLine().strip();
    }

    /**
     * Checks if the user is exiting the application.
     *
     * @param input The user's input.
     * @return true if the user is exiting, false otherwise.
     */
    private static boolean isUserExiting(String input) {
        return input.equals(ExitCommand.CLI_REPRESENTATION);
    }

    /**
     * Prints a message indicating that the application is reading from local data.
     */
    public static void printReadInitialisation() {
        System.out.println("Reading from local data...");
    }

    /**
     * Prints a message indicating that no file is available.
     */
    public static void printDidNotRead() {
        System.out.println("No file is available.");
    }

    /**
     * Prints a message indicating that the file read is empty.
     */
    public static void printFileReadIsEmpty() {
        System.out.println("File is empty.");
    }

    /**
     * Prints a message indicating the number of tasks successfully read.
     *
     * @param count The number of tasks read.
     */
    public static void printReadSuccess(int count) {
        System.out.println("Successfully read *" + count + "* "
            + (count == 1 ? "task" : "tasks") + "!");
    }

    /**
     * Prints a message indicating a failure to read tasks.
     *
     * @param e The exception that occurred during reading.
     */
    public static void printReadFailure(Exception e) {
        System.out.println("Unable to read any tasks.\n(Error: " + e + ")");
    }

    /**
     * Prints a message indicating that the application is writing to local data.
     */
    public static void printWriteInitialisation() {
        System.out.println("\nWriting to local data...");
    }

    /**
     * Prints a message indicating the number of tasks successfully written.
     *
     * @param count The number of tasks written.
     */
    public static void printWriteSuccess(int count) {
        System.out.println("Successfully wrote *" + count + "* "
            + (count == 1 ? "task" : "tasks") + "!");
    }

    /**
     * Prints a message indicating a failure to write tasks.
     *
     * @param e The exception that occurred during writing.
     */
    public static void printWriteFailure(Exception e) {
        System.out.println("Unable to write any tasks.\n(Error: " + e + ")");
    }

}
