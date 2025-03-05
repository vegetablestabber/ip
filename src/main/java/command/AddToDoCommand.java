package command;

import error.AppException;
import io.ArgumentReader;
import task.ToDo;

public class AddToDoCommand extends AddCommand {
    public static final String CLI_REPRESENTATION = "todo";

    public AddToDoCommand(String[] args) {
        super(args);
    }

    @Override
    public ToDo getAddedTask() throws AppException {
        String description = ArgumentReader.retriveArgValue(this.args);
        ToDo toDo = new ToDo(description);

        return toDo;
    }
}