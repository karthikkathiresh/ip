package nimbus;

import static nimbus.tasks.Parser.parse;

import java.io.FileNotFoundException;
import java.io.IOException;

import nimbus.command.Command;
import nimbus.exceptions.NimbusException;
import nimbus.storage.Storage;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

/**
 * The main entry point for the Nimbus application.
 * Initializes the required components and manages the main execution loop.
 */
public class Nimbus {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Nimbus instance, setting up the UI, Storage, and TaskList.
     *
     * @param filePath The relative path to the file where task data is saved.
     */
    public Nimbus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (NimbusException | FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Starting with an empty task list.");
            taskList = new TaskList();
        }

    }

    /**
     * Runs the main execution loop of the application.
     * Repeatedly prompts the user for commands and executes them until an exit command is given.
     *
     * @throws NimbusException If an unexpected error occurs during command execution.
     * @throws IOException If an error occurs while saving the task list.
     */
    public void run() throws NimbusException, IOException {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String userCommand = ui.getUserCommand();
            if (userCommand.isEmpty()) {
                continue;
            }

            try {
                Command c = parse(userCommand);
                c.execute(taskList, ui);
                isExit = c.isExit();
                if (isExit) {
                    storage.save(taskList);
                }
            } catch (NimbusException e) {
                ui.printToScreen(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws NimbusException, IOException {
        String filePath = "data/nimbus.txt";
        if (args.length > 0) {
            filePath = args[0];
        }
        new Nimbus(filePath).run();
    }
}
