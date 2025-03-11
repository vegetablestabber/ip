package bot;

import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import task.TaskList;

/**
 * Represents a bot that handles user input and manages tasks.
 */
public class Bot {

    private final TaskList tasks;

    /**
     * Constructs a Bot with the given TaskList.
     *
     * @param tasks The list of tasks.
     */
    public Bot(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Handles the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @return The corresponding command.
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws DateTimeParseException If date strings are not formatted properly.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     */
    public Command handleUserInput(String input) throws IllegalArgumentException,
        DateTimeParseException, IndexOutOfBoundsException {
        String[] args = input.split(" ");

        switch (args[0]) {
        case ExitCommand.CLI_REPRESENTATION:
            return new ExitCommand(args);
        case AddToDoCommand.CLI_REPRESENTATION:
            return addToDo(args);
        case AddDeadlineCommand.CLI_REPRESENTATION:
            return addDeadline(args);
        case AddEventCommand.CLI_REPRESENTATION:
            return addEvent(args);
        case DeleteCommand.CLI_REPRESENTATION:
            return deleteTask(args);
        case MarkCommand.CLI_REPRESENTATION:
            return markTask(args);
        case UnmarkCommand.CLI_REPRESENTATION:
            return unmarkTask(args);
        case ListCommand.CLI_REPRESENTATION:
            return listTasks(args);
        case FindCommand.CLI_REPRESENTATION:
            return findTask(args);
        default:
            throw new IllegalArgumentException("Input '" + input + "' is invalid.");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param <T> The type of the AddCommand.
     * @param command The command to add the task.
     * @return The command with the added task.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    private <T extends AddCommand> T addTask(T command) throws IllegalArgumentException {
        this.tasks.add(command.getAddedTask());
        return command;
    }

    /**
     * Adds a to-do task.
     *
     * @param args The arguments for the to-do task.
     * @return The AddToDoCommand with the added task.
     * @throws IllegalArgumentException If there is an illegal argument.
     */
    private AddToDoCommand addToDo(String[] args) throws IllegalArgumentException {
        AddToDoCommand command = new AddToDoCommand(args);
        return addTask(command);
    }

    /**
     * Adds a deadline task.
     *
     * @param args The arguments for the deadline task.
     * @return The AddDeadlineCommand with the added task.
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws DateTimeParseException If date strings are not formatted properly.
     */
    private AddDeadlineCommand addDeadline(String[] args)
        throws IllegalArgumentException, DateTimeParseException {
        AddDeadlineCommand command = new AddDeadlineCommand(args);
        return addTask(command);
    }

    /**
     * Adds an event task.
     *
     * @param args The arguments for the event task.
     * @return The AddEventCommand with the added task.
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws DateTimeParseException If date strings are not formatted properly.
     */
    private AddEventCommand addEvent(String[] args)
        throws IllegalArgumentException, DateTimeParseException {
        AddEventCommand command = new AddEventCommand(args);
        return addTask(command);
    }

    /**
     * Deletes a task.
     *
     * @param args The arguments for the delete command.
     * @return The DeleteCommand with the deleted task.
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     */
    private DeleteCommand deleteTask(String[] args)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        DeleteCommand command = new DeleteCommand(args, this.tasks);
        return this.tasks.delete(command.getTaskIndex(),
            task -> command.updateTask(task));
    }

    /**
     * Marks a task as done.
     *
     * @param args The arguments for the mark command.
     * @return The MarkCommand with the marked task.
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     */
    private MarkCommand markTask(String[] args)
            throws IllegalArgumentException, IndexOutOfBoundsException {
        MarkCommand command = new MarkCommand(args, this.tasks);
        return this.tasks.mark(command.getTaskIndex(),
            task -> command.updateTask(task));
    }

    /**
     * Unmarks a task as not done.
     *
     * @param args The arguments for the unmark command.
     * @return The UnmarkCommand with the unmarked task.
     * @throws IllegalArgumentException If there is an illegal argument.
     * @throws IndexOutOfBoundsException If the task number is out of bounds.
     */
    private UnmarkCommand unmarkTask(String[] args)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        UnmarkCommand command = new UnmarkCommand(args, this.tasks);
        return this.tasks.unmark(command.getTaskIndex(),
            task -> command.updateTask(task));
    }

    /**
     * Lists all tasks.
     *
     * @param args The arguments for the list command.
     * @return The ListCommand with the list of tasks.
     */
    private ListCommand listTasks(String[] args) {
        return new ListCommand(args, this.tasks);
    }
    
    /**
     * Finds tasks filtered by their descriptions containing a given keyword.
     *
     * @param args The arguments for the find command.
     * @return The FindCommand with the list of tasks.
     */
    private FindCommand findTask(String[] args) {
        return new FindCommand(args, this.tasks);
    }

}