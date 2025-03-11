package io;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import bot.Bot;
import command.Command;
import command.ExitCommand;
import storage.TaskWriter;

/**
 * Represents the user interface for interacting with the bot.
 */
public class UI {

    /** The logo displayed when starting the application. */
    public static final String LOGO = "     ______ ____________________\n" + "    / __/ //_/ __/ __/_  __/ __/\n"
            + "   _\\ \\/ ,< / _// _/  / / _\\ \\  \n" + "  /___/_/|_/___/___/ /_/ /___/  ";

    /**
     * Connects the UI to the bot and starts the interaction loop.
     *
     * @param bot The bot to connect to.
     */
    public void connect(Bot bot) {
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
            }catch (DateTimeParseException e) {
                System.out.println("Error: '" + e.getParsedString()
                    + "' is not of the format '" + TaskWriter.getDateFormat() + "'.");
            }
        } while (!isUserExiting(input));

        scanner.close();
    }

    /**
     * Displays the welcome message and logo to the user.
     */
    private void greetUser() {
        System.out.println(LOGO + "\n\nBend your knee and ask what you desire.");
    }

    /**
     * Requests and retrieves input from the user.
     *
     * @param scanner The scanner to read input from.
     * @return The user's input as a string.
     */
    private String requestUserInput(Scanner scanner) {
        System.out.print("\nWhat do you want? ");
        return scanner.nextLine().strip();
    }

    /**
     * Checks if the user is exiting the application.
     *
     * @param input The user's input.
     * @return true if the user is exiting, false otherwise.
     */
    private boolean isUserExiting(String input) {
        return input.isEmpty() || input.equals(ExitCommand.CLI_REPRESENTATION);
    }

    public void printReadInitialisation() {
        System.out.println("Reading from local data...");
    }

    public void printDidNotRead() {
        System.out.println("No file is available.");
    }

    public void printFileReadIsEmpty() {
        System.out.println("File is empty.");
    }

    public void printReadSuccess(int count) {
        System.out.println("Successfully read *" + count + "* tasks!");
    }

    public void printReadFailure(Exception e) {
        System.out.println("Unable to read any tasks.\n(Error: " + e + ")");
    }

    public void printWriteInitialisation() {
        System.out.println("\nWriting to local data...");
    }

    public void printWriteSuccess(int count) {
        System.out.println("Successfully wrote *" + count + "* tasks!");
    }

    public void printWriteFailure(Exception e) {
        System.out.println("Unable to write any tasks.\n(Error: " + e + ")");
    }

}
