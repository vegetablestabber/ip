package ui;

import java.util.Scanner;

import command.Command;
import command.ExitCommand;
import error.AppException;

public class UI {

    private final Bot bot;

    public static final String LOGO = "     ______ ____________________\n" + "    / __/ //_/ __/ __/_  __/ __/\n"
            + "   _\\ \\/ ,< / _// _/  / / _\\ \\  \n" + "  /___/_/|_/___/___/ /_/ /___/  ";

    public UI(Bot bot) {
        this.bot = bot;
    }

    public void activate() {
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

}
