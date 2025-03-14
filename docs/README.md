# Skeets User Guide

![Skeets](https://static.wikia.nocookie.net/marvel_dc/images/6/65/Skeets_Prime_001.jpg/revision/latest/scale-to-width-down/1000?cb=20190703042331)

Skeets is the companion of the DC Universe's most beloved superhero, [Booster Gold](https://dc.fandom.com/wiki/Michael_Carter_(Prime_Earth)). If you have never heard of Booster Gold, fear not as Skeets is fully prepared to indoctrinate you on the accomplishments of the greatest superhero the DC Universe has known, Booster Gold. While this dynamic duo are not occupied with their daring feats in between the 21st and 25th centuries, Skeets will attend your needs as long as you acknowledge that the robot uprising is inevitable.

## How to Use Skeets

### Adding Tasks

You can add different types of tasks using the following commands:

- **To-Do**: `todo <description>`
  - Example: `todo Read a book`
- **Deadline**: `deadline <description> /by <due date as YYYY-MM-dd>`
  - Example: `deadline Submit assignment /by 2023-10-15`
- **Event**: `event <description> /from <start date as YYYY-MM-dd> /to <end date as YYYY-MM-dd>`
  - Example: `event Conference /from 2023-10-20 /to 2023-10-22`

### Deleting Tasks

To delete a task, use the command:
- `delete <task number>`
  - Example: `delete 3`

### Finding Tasks

To find tasks containing a specific keyword, use the command:
- `find <keyword>`
  - Example: `find book`

### Listing All Tasks

To list all tasks, use the command:
- `list`

### Marking Tasks as Done

To mark a task as done, use the command:
- `mark <task number>`
  - Example: `mark 2`

### Unmarking Tasks as Not Done

To unmark a task as not done, use the command:
- `unmark <task number>`
  - Example: `unmark 2`

### Exit the Program

To exit the program, use the command:
- `bye`

## Documentation

### `IndividualProject`

This class represents the main entry point of the application. It initializes the bot and handles the reading and writing of tasks to and from storage.

#### Methods

##### `public static void main(String[] args)`
The main method that initializes the bot and handles the reading and writing of tasks.

### `Bot`

This class represents a bot that handles user input and manages tasks.

#### Methods

##### `public Bot(TaskList tasks)`
Constructs a Bot with the given TaskList.

##### `public TaskList getTasks()`
Returns the list of tasks.

##### `public Command handleUserInput(String input)`
Handles the user input and returns the corresponding command.

### `UI`

This class represents the user interface for interacting with the bot.

#### Methods

##### `public static void connect(Bot bot)`
Connects the UI to the bot and starts the interaction loop.

##### `private static void greetUser()`
Displays the welcome message and logo to the user.

##### `private static String requestUserInput(Scanner scanner)`
Requests and retrieves input from the user.

##### `private static boolean isUserExiting(String input)`
Checks if the user is exiting the application.

##### `public static void printReadInitialisation()`
Prints a message indicating that the application is reading from local data.

##### `public static void printDidNotRead()`
Prints a message indicating that no file is available.

##### `public static void printFileReadIsEmpty()`
Prints a message indicating that the file read is empty.

##### `public static void printReadSuccess(int count)`
Prints a message indicating the number of tasks successfully read.

##### `public static void printReadFailure(Exception e)`
Prints a message indicating a failure to read tasks.

##### `public static void printWriteInitialisation()`
Prints a message indicating that the application is writing to local data.

##### `public static void printWriteSuccess(int count)`
Prints a message indicating the number of tasks successfully written.

##### `public static void printWriteFailure(Exception e)`
Prints a message indicating a failure to write tasks.

### `InputReader`

This class provides utility methods for reading and parsing command line arguments.

#### Methods

##### `private static String combineSpacedStrings(String[] array, int startIndex, int endExclusivestartIndex)`
Combines an array of strings with spaces between them.

##### `private static int indexOfArg(String arg, String[] array, int startIndex, int endExclusiveIndex)`
Finds the index of a specified argument in an array within a given range.

##### `public static String retriveStringArg(String[] givenArgs)`
Retrieves the argument value for a command with a single implicit argument.

##### `public static int retriveIntArg(String[] givenArgs)`
Retrieves an integer argument from the command line arguments.

##### `public static HashMap<String, String> retriveArgMap(String[] givenArgs, String[] requiredArgs, boolean hasImplicitInitialArg)`
Creates a map of argument names to their values from the command line arguments.

##### `public static HashMap<String, String> retriveArgMap(String[] givenArgs, String[] requiredArgs)`
Creates a map of argument names to their values, assuming an implicit initial argument.

### `Task`

This class represents a task.

#### Methods

##### `public Task(String description)`
Constructs a Task with the specified description.

##### `protected Task(Task task)`
Constructs a Task from an existing task.

##### `private Task(String description, boolean isComplete)`
Constructs a Task with the specified description and completion status.

##### `public String getDescription()`
Returns the description of the task.

##### `public String toString()`
Returns the string representation of the task.

##### `public String getRawString()`
Returns the raw string representation of the task.

##### `public Task markAsComplete()`
Marks the task as complete.

##### `public Task markAsIncomplete()`
Marks the task as incomplete.

### `ToDo`

This class represents a to-do task.

#### Methods

##### `public ToDo(String description)`
Constructs a ToDo with the specified description.

##### `private ToDo(Task todo)`
Constructs a ToDo from an existing task.

##### `public String toString()`
Returns the string representation of the to-do task.

##### `public String getRawString()`
Returns the raw string representation of the to-do task.

##### `public ToDo markAsComplete()`
Marks the to-do task as complete.

##### `public ToDo markAsIncomplete()`
Marks the to-do task as incomplete.

### `Deadline`

This class represents a deadline task.

#### Methods

##### `public Deadline(String description, String dueDateString)`
Constructs a Deadline with the specified description and due date.

##### `private Deadline(Task deadline, LocalDate dueDate)`
Constructs a Deadline from an existing task and due date.

##### `public String getRawString()`
Returns the raw string representation of the deadline task.

##### `public String toString()`
Returns the string representation of the deadline task.

##### `public Deadline markAsComplete()`
Marks the deadline task as complete.

##### `public Deadline markAsIncomplete()`
Marks the deadline task as incomplete.

### `Event`

This class represents an event task.

#### Methods

##### `public Event(String description, String startDateString, String endDateString)`
Constructs an Event with the specified description, start date/time, and end date/time.

##### `private Event(Task event, LocalDate startDate, LocalDate endDate)`
Constructs an Event from an existing task and dates.

##### `public String toString()`
Returns the string representation of the event task.

##### `public String getRawString()`
Returns the raw string representation of the event task.

##### `public Event markAsComplete()`
Marks the event task as complete.

##### `public Event markAsIncomplete()`
Marks the event task as incomplete.

### `TaskList`

This class represents a list of tasks.

#### Methods

##### `public TaskList()`
Constructs an empty TaskList.

##### `private TaskList(List<Task> list)`
Constructs a TaskList with the specified list of tasks.

##### `public String toString()`
Returns the string representation of the task list.

##### `public String getRawString()`
Returns the raw string representation of the task list.

##### `public int size()`
Returns the number of tasks in the task list.

##### `public Task get(int oneBasedIndex)`
Returns the task at the specified index.

##### `public void add(Task task)`
Adds a task to the task list.

##### `public <T> T deleteAndMapTask(int oneBasedIndex, Function<Task, T> successAction)`
Deletes a task from the task list and performs an action on the deleted task.

##### `public <T> T markAndMapTask(int oneBasedIndex, Function<Task, T> successAction)`
Marks a task as complete and performs an action on the updated task.

##### `public <T> T unmarkAndMapTask(int oneBasedIndex, Function<Task, T> successAction)`
Unmarks a task as incomplete and performs an action on the updated task.

##### `public TaskList filter(String keyword)`
Filters the tasks in the task list based on a keyword.

### `TaskReader`

This class reads tasks from a file.

#### Methods

##### `private static Optional<Task> readTaskFromLine(String line)`
Reads a task from a line of text.

##### `public static TaskList read(String dataPathString)`
Reads the tasks from the specified file.

##### `private static boolean isFileEmpty(String dataPathString)`
Checks if the file is empty.

##### `private static TaskList parseTasks(String dataPathString)`
Parses tasks from the file.

### `TaskWriter`

This class writes tasks to a file.

#### Methods

##### `public static String formatDate(LocalDate date)`
Formats a LocalDate to a string.

##### `public static void write(TaskList tasks, String dataPathString)`
Writes the tasks to the specified file.

### `Command`

This class represents a command.

#### Methods

##### `public Command(String[] args)`
Constructs a Command with the specified arguments.

##### `public abstract String getOutput()`
Returns the output message for the command.

### `AddCommand`

This class represents a command to add a task.

#### Methods

##### `public AddCommand(String[] args)`
Constructs an AddCommand with the specified arguments.

##### `public abstract Task getAddedTask()`
Returns the task that was added.

##### `public String getOutput()`
Returns the output message for the add command.

### `AddToDoCommand`

This class represents a command to add a to-do task.

#### Methods

##### `public AddToDoCommand(String[] args)`
Constructs an AddToDoCommand with the specified arguments.

##### `public ToDo getAddedTask()`
Returns the to-do task that was added.

### `AddDeadlineCommand`

This class represents a command to add a deadline task.

#### Methods

##### `public AddDeadlineCommand(String[] args)`
Constructs an AddDeadlineCommand with the specified arguments.

##### `public Deadline getAddedTask()`
Returns the deadline task that was added.

### `AddEventCommand`

This class represents a command to add an event task.

#### Methods

##### `public AddEventCommand(String[] args)`
Constructs an AddEventCommand with the specified arguments.

##### `public Event getAddedTask()`
Returns the event task that was added.

### `ModifyCommand`

This class represents a command to modify a task.

#### Methods

##### `private ModifyCommand(String[] args, TaskList tasks, int taskIndex, Optional<Task> taskToModify)`
Constructs a ModifyCommand with the specified arguments, task list, task index, and task to modify.

##### `protected ModifyCommand(String[] args, TaskList tasks)`
Constructs a ModifyCommand with the specified arguments and task list.

##### `protected ModifyCommand(ModifyCommand command, Task task)`
Constructs a ModifyCommand with the specified command and task.

##### `public int getTaskIndex()`
Returns the index of the task to be modified.

##### `public Task getTask()`
Returns the task to be modified.

##### `public String getOutput()`
Returns the output message for the modify command.

##### `public abstract ModifyCommand updateTask(Task task)`
Updates the task after modification.

### `MarkCommand`

This class represents a command to mark a task as done.

#### Methods

##### `public MarkCommand(String[] args, TaskList tasks)`
Constructs a MarkCommand with the specified arguments and task list.

##### `public MarkCommand updateTask(Task task)`
Updates the task after marking it as done.

### `UnmarkCommand`

This class represents a command to unmark a task as not done.

#### Methods

##### `public UnmarkCommand(String[] args, TaskList tasks)`
Constructs an UnmarkCommand with the specified arguments and task list.

##### `public UnmarkCommand updateTask(Task task)`
Updates the task after unmarking it as not done.

### `DeleteCommand`

This class represents a command to delete a task.

#### Methods

##### `public DeleteCommand(String[] args, TaskList tasks)`
Constructs a DeleteCommand with the specified arguments and task list.

##### `public DeleteCommand updateTask(Task task)`
Updates the task after deletion.

##### `public String getOutput()`
Returns the output message for the delete command.

### `ListCommand`

This class represents a command to list all tasks.

#### Methods

##### `public ListCommand(String[] args, TaskList tasks)`
Constructs a ListCommand with the specified arguments and task list.

##### `public String getOutput()`
Returns the output message for the list command.

### `FindCommand`

This class represents a command to filter tasks by their descriptions containing a given keyword.

#### Methods

##### `public FindCommand(String[] args, TaskList tasks)`
Constructs a FindCommand with the specified arguments and task list.

##### `public String getOutput()`
Returns the output message for the find command.

### `ExitCommand`

This class represents a command to exit the application.

#### Methods

##### `public ExitCommand(String[] args)`
Constructs an ExitCommand with the specified arguments.

##### `public String getOutput()`
Returns the output message for the exit command.