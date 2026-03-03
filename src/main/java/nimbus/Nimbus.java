package nimbus;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import nimbus.command.DeadlineCommand;
import nimbus.command.DeleteCommand;
import nimbus.command.EventCommand;
import nimbus.command.ExitCommand;
import nimbus.command.ListCommand;
import nimbus.command.MarkCommand;
import nimbus.command.ToDoCommand;
import nimbus.command.UnmarkCommand;
import nimbus.exceptions.NimbusException;
import nimbus.storage.FileReader;
import nimbus.storage.FileSaver;
import nimbus.tasks.Parser;
import nimbus.tasks.TaskHandler;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public class Nimbus {

    public static TaskList taskList;
    private static Ui ui;

    public Nimbus() {
        taskList = new TaskList();
        ui = new Ui();
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
        ui.printWelcomeMessage();

        while (true) {
            userCommand = ui.getUserCommand();

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
            new ListCommand().execute(taskList, ui);
            break;

        case Parser.COMMAND_DELETE:
            new DeleteCommand(description).execute(taskList, ui);
            break;

        case Parser.COMMAND_MARK:
            new MarkCommand(description).execute(taskList, ui);
            break;

        case Parser.COMMAND_UNMARK:
            new UnmarkCommand(description).execute(taskList, ui);
            break;

        case Parser.COMMAND_EXIT:
            new ExitCommand().execute(taskList, ui);
            FileSaver.write(f, taskList);
            System.exit(0);

        case Parser.COMMAND_TODO:
            new ToDoCommand(description).execute(taskList, ui);
            break;

        case Parser.COMMAND_DEADLINE:
            String descDeadline = Parser.extractDescriptionForDeadline(description);
            String by = Parser.extractDeadline(description);
            new DeadlineCommand(descDeadline, by).execute(taskList, ui);
            break;

        case Parser.COMMAND_EVENT:
            String descEvent = Parser.extractDescriptionForEvent(description);
            String from = Parser.extractFrom(description);
            String to = Parser.extractTo(description);
            new EventCommand(descEvent, from, to).execute(taskList, ui);
            break;

        default:
            ui.printInvalidTaskMessage();
        }
    }

}
