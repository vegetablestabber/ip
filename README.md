# Introduction to Skeets

Skeets is a personal assistant chatbot that helps you manage your tasks efficiently. It can handle different types of tasks such as todos, deadlines, and events.

![Skeets](https://static.wikia.nocookie.net/marvel_dc/images/6/65/Skeets_Prime_001.jpg/revision/latest/scale-to-width-down/1000?cb=20190703042331)

# Features

- Add, delete, and list tasks
- Mark tasks as done
- Handle different types of tasks: todos, deadlines, and events
- Save tasks to a file and load them back

# Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
   1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
      In the same dialog, set the **Project language level** field to the `SDK default` option.
   1. After that, locate the `src/main/java/IndividualProject.java` file, right-click it, and choose `Run IndividualProject.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
      ```
      Reading from local data...
      Successfully read *X* tasks!
         ______ ____________________
        / __/ //_/ __/ __/_  __/ __/
       _\ \/ ,< / _// _/  / / _\ \
      /___/_/|_/___/___/ /_/ /___/

      Bend your knee and ask what you desire.

      What do you want?
      ```

# Compiling and Running

To compile and run the program from the command line:

1. Navigate to the project directory.
1. Compile the source files:
   ```sh
   javac -d bin src/main/java/*.java
   ```
1. Run the program:
   ```sh
   java -cp bin IndividualProject
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
