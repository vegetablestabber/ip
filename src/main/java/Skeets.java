import java.util.Scanner;

public class Skeets {
    public static String LOGO = "     ______ ____________________\n"
        + "    / __/ //_/ __/ __/_  __/ __/\n"
        + "   _\\ \\/ ,< / _// _/  / / _\\ \\  \n"
        + "  /___/_/|_/___/___/ /_/ /___/  ";

    public static void main(String[] args) {
        greetUser();
        
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = requestUserInput(scanner);
            handleUserInput(input);
        } while (!isUserExiting(input));

        scanner.close();
        dismissUser();
    }

    public static void greetUser() {
        System.out.print(LOGO + "\n\nBend your knee and ask what you desire.");
    }

    public static void dismissUser() {
        System.out.print("\n\nYou are dismissed.");
    }

    public static boolean isUserExiting(String input) {
        return input.isEmpty() || input.equals("bye");
    }

    public static String requestUserInput(Scanner scanner) {
        System.out.print("\n\nWhat do you want? ");
        return scanner.nextLine().strip();
    }

    public static void handleUserInput(String input) {
        System.out.print("Input: " + input);
    }
}