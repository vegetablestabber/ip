package command;

public class ExitCommand extends Command {

    public static final String CLI_REPRESENTATION = "bye";

    public ExitCommand(String[] args) {
        super(args);
    }

    @Override
    public String getOutput() {
        return "\nYou are dismissed.";
    }

}
