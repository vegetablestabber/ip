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

public class Bot {

    private final TaskList tasks;

    public Bot(TaskList tasks) {
        this.tasks = tasks;
    }

    public TaskList getTasks() {
        return this.tasks;
    }

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

    private <T extends AddCommand> T addTask(T command) throws IllegalArgumentException {
        this.tasks.add(command.getAddedTask());
        return command;
    }

    private AddToDoCommand addToDo(String[] args) throws IllegalArgumentException {
        AddToDoCommand command = new AddToDoCommand(args);
        return addTask(command);
    }

    private AddDeadlineCommand addDeadline(String[] args)
        throws IllegalArgumentException, DateTimeParseException {
        AddDeadlineCommand command = new AddDeadlineCommand(args);
        return addTask(command);
    }

    private AddEventCommand addEvent(String[] args)
        throws IllegalArgumentException, DateTimeParseException {
        AddEventCommand command = new AddEventCommand(args);
        return addTask(command);
    }

    private DeleteCommand deleteTask(String[] args)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        DeleteCommand command = new DeleteCommand(args, this.tasks);
        return this.tasks.delete(command.getTaskIndex(),
            task -> command.updateTask(task));
    }

    private MarkCommand markTask(String[] args) throws NumberFormatException,
            IndexOutOfBoundsException, MissingArgumentException {
        MarkCommand command = new MarkCommand(args, this.tasks);
        return this.tasks.mark(command.getTaskIndex(),
            task -> command.updateTask(task));
    }

    private UnmarkCommand unmarkTask(String[] args)
        throws IllegalArgumentException, IndexOutOfBoundsException {
        UnmarkCommand command = new UnmarkCommand(args, this.tasks);
        return this.tasks.unmark(command.getTaskIndex(),
            task -> command.updateTask(task));
    }

    private ListCommand listTasks(String[] args) {
        return new ListCommand(args, this.tasks);
    }

    private FindCommand findTask(String[] args) throws MissingArgumentException {
        return new FindCommand(args, this.tasks);
    }

}