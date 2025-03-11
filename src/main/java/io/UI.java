package io;

import java.util.Scanner;

import bot.Bot;
import command.Command;
import command.ExitCommand;
import error.AppException;

public class UI {

    public static final String LOGO = "     ______ ____________________\n" + "    / __/ //_/ __/ __/_  __/ __/\n"
            + "   _\\ \\/ ,< / _// _/  / / _\\ \\  \n" + "  /___/_/|_/___/___/ /_/ /___/  ";

    public void connect(Bot bot) {
        greetUser();
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            input = requestUserInput(scanner);

            try {
                Command command = bot.handleUserInput(input);
                System.out.println(command.getOutput());
            } catch (AppException e) {
                System.err.println("App error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("System error: " + e.getMessage());
            }
        } while (!isUserExiting(input));

        scanner.close();
    }

    private void greetUser() {
        System.out.println(LOGO + "\n\nBend your knee and ask what you desire.");
    }

    private String requestUserInput(Scanner scanner) {
        System.out.print("\nWhat do you want? ");
        return scanner.nextLine().strip();
    }

    private boolean isUserExiting(String input) {
        return input.isEmpty() || input.equals(ExitCommand.CLI_REPRESENTATION);
    }

    public void printReadInitialisation() {
        System.out.println("Reading from local data...");
    }

    public void printReadSuccess(int count) {
        System.out.println("Successfully read *" + count + "* tasks!");
    }

    public void printReadFailure(Exception e) {
        System.out.println("Error: " + e + "\nUnable to read any tasks.");
    }

    public void printWriteInitialisation() {
        System.out.println("Writing to local data...");
    }

    public void printWriteSuccess(int count) {
        System.out.println("Successfully wrote *" + count + "* tasks!");
    }

    public void printWriteFailure(Exception e) {
        System.out.println("Error: " + e + "\nUnable to write any tasks.");
    }

}
