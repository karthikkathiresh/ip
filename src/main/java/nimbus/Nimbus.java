package nimbus;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import nimbus.exceptions.NimbusException;
import nimbus.storage.FileReader;
import nimbus.storage.FileSaver;
import nimbus.tasks.Parser;
import nimbus.tasks.TaskHandler;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public class Nimbus {

    private static TaskList taskList;
    private static Ui ui;

    public Nimbus() {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public static void main(String[] args) {

        new Nimbus();

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
        ui.printWelcomeMessage();

        while (in.hasNextLine()) {
            userCommand = in.nextLine().trim();

            if (userCommand.isEmpty()) {
                continue;
            }

            try {
                run(userCommand, f);
            } catch (NimbusException | IOException e) {
                Ui.printLine();
                System.out.println(e.getMessage());
                Ui.printLine();
            }
        }
    }

    private static void run(String userCommand, File f) throws NimbusException, IOException {
        String[] parts = userCommand.split(" ", 2);
        String commandWord = parts[0];
        String description = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandWord) {

        case Parser.COMMAND_LIST:
            ui.printListOfTasks(taskList);
            break;

        case Parser.COMMAND_DELETE:
            TaskHandler.delete(description, taskList, ui);
            break;

        case Parser.COMMAND_MARK:
            TaskHandler.mark(description, taskList, ui);
            break;

        case Parser.COMMAND_UNMARK:
            TaskHandler.unmark(description, taskList, ui);
            break;

        case Parser.COMMAND_EXIT:
            ui.printGoodbyeMessage();
            FileSaver.write(f, taskList);
            System.exit(0);

        case Parser.COMMAND_TODO:
            TaskHandler.handleToDo(description, taskList, ui);
            break;

        case Parser.COMMAND_DEADLINE:
            TaskHandler.handleDeadline(description, taskList, ui);
            break;

        case Parser.COMMAND_EVENT:
            TaskHandler.handleEvent(description, taskList, ui);
            break;

        default:
            ui.printInvalidTaskMessage();
        }
    }

}
