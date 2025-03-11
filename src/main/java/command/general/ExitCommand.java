package command.general;

import command.Command;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    public static final String CLI_REPRESENTATION = "bye";

    /**
     * Constructs an ExitCommand with the specified arguments.
     *
     * @param args The arguments for the command.
     */
    public ExitCommand(String[] args) {
        super(args);
    }

    /**
     * Returns the output message for the exit command.
     *
     * @return The output message.
     */
    @Override
    public String getOutput() {
        return "\nYou are dismissed.";
    }

}
