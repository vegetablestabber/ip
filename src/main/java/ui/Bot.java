package ui;

import java.io.IOException;

import command.*;
import error.AppException;
import error.MissingArgumentException;
import io.DataManager;
import task.TaskList;

public class Bot {

    private final TaskList tasks;

    public Bot() {
        TaskList tasks = new TaskList();

        try {
            tasks = DataManager.readData();
        } catch (IOException e) {

        }

        this.tasks = tasks;
    }

    public Command handleUserInput(String input) throws AppException {
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
        case ListCommand.CLI_REPRESENTATION:
            return listTasks(args);
        case MarkCommand.CLI_REPRESENTATION:
            return markTask(args);
        case UnmarkCommand.CLI_REPRESENTATION:
            return unmarkTask(args);
        default:
            throw new AppException("Input '" + input + "' is invalid.");
        }
    }

    private <T extends AddCommand> T addTask(T command) throws AppException {
        this.tasks.add(command.getAddedTask());
        return command;
    }

    private AddToDoCommand addToDo(String[] args) throws AppException {
        AddToDoCommand command = new AddToDoCommand(args);
        return addTask(command);
    }

    private AddDeadlineCommand addDeadline(String[] args) throws AppException {
        AddDeadlineCommand command = new AddDeadlineCommand(args);
        return addTask(command);
    }

    private AddEventCommand addEvent(String[] args) throws AppException {
        AddEventCommand command = new AddEventCommand(args);
        return addTask(command);
    }

    private DeleteCommand deleteTask(String[] args) throws NumberFormatException,
            IndexOutOfBoundsException, MissingArgumentException {
        DeleteCommand command = new DeleteCommand(args, this.tasks);
        return this.tasks.delete(command.getTaskIndex(), () -> command);
    }

    private ListCommand listTasks(String[] args) {
        return new ListCommand(args, this.tasks);
    }

    private UnmarkCommand markTask(String[] args) throws NumberFormatException,
            IndexOutOfBoundsException, MissingArgumentException {
        UnmarkCommand command = new UnmarkCommand(args, this.tasks);
        return this.tasks.mark(command.getTaskIndex(), () -> command);
    }

    private UnmarkCommand unmarkTask(String[] args) throws NumberFormatException,
            IndexOutOfBoundsException, MissingArgumentException {
        UnmarkCommand command = new UnmarkCommand(args, this.tasks);
        return this.tasks.unmark(command.getTaskIndex(), () -> command);
    }

}