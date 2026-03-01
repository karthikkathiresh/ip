package nimbus;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import nimbus.exceptions.NimbusException;
import nimbus.storage.FileReader;
import nimbus.storage.FileSaver;
import nimbus.tasks.Parser;
import nimbus.tasks.Task;
import nimbus.tasks.TaskHandler;
import nimbus.tasks.TaskList;
import nimbus.ui.MessagePrinter;

public class Main {

    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {

        File f = new File("data/nimbus.txt");

        try {
            if (f.getParentFile() != null && !f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("No existing file found. Created a new one at data/nimbus.txt!");
            } else {
                FileReader.read(f, taskList);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while setting up the save file: " + e.getMessage());
        }

        String userCommand;
        Scanner in = new Scanner(System.in);
        MessagePrinter.printWelcomeMessage();

        while (in.hasNextLine()) {
            userCommand = in.nextLine().trim();

            if (userCommand.isEmpty()) {
                continue;
            }

            try {
                run(userCommand, f);
            } catch (NimbusException | IOException e) {
                MessagePrinter.printLine();
                System.out.println(e.getMessage());
                MessagePrinter.printLine();
            }
        }
    }

    private static void run(String userCommand, File f) throws NimbusException, IOException {
        String[] parts = userCommand.split(" ", 2);
        String commandWord = parts[0];
        String description = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandWord) {

        case Parser.COMMAND_LIST:
            MessagePrinter.printListOfTasks(taskList);
            break;

        case Parser.COMMAND_DELETE:
            int index = Integer.parseInt(parts[1]);
            TaskHandler.delete(index, taskList);
            break;

        case Parser.COMMAND_MARK:
            index = Integer.parseInt(parts[1]);
            TaskHandler.mark(index, taskList);
            break;

        case Parser.COMMAND_UNMARK:
            index = Integer.parseInt(parts[1]);
            TaskHandler.unmark(index, taskList);
            break;

        case Parser.COMMAND_EXIT:
            MessagePrinter.printGoodbyeMessage();
            FileSaver.write(f, taskList);
            System.exit(0);

        case Parser.COMMAND_TODO:
            TaskHandler.handleToDo(description, taskList);
            break;

        case Parser.COMMAND_DEADLINE:
            TaskHandler.handleDeadline(description, taskList);
            break;

        case Parser.COMMAND_EVENT:
            TaskHandler.handleEvent(description, taskList);
            break;

        default:
            MessagePrinter.printInvalidTaskMessage();
        }
    }

}
