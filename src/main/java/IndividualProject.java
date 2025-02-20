import java.util.Scanner;

public class IndividualProject {
    public static void main(String[] args) {
        Skeets skeets = new Skeets();
        Scanner scanner = new Scanner(System.in);

        skeets.activate(scanner);
        scanner.close();
    }
}