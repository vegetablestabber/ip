package command;

/**
 * Represents a command.
 */
public abstract class Command {

    protected final String[] args;

    /**
     * Constructs a Command with the specified arguments.
     *
     * @param args The arguments for the command.
     */
    public Command(String[] args) {
        this.args = args;
    }

    /**
     * Returns the output message for the command.
     *
     * @return The output message.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    public abstract String getOutput() throws IllegalArgumentException;

}