package nimbus;

import static nimbus.tasks.Parser.parse;

import java.io.FileNotFoundException;
import java.io.IOException;

import nimbus.command.Command;
import nimbus.exceptions.NimbusException;
import nimbus.storage.Storage;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public class Nimbus {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

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

    public void run() throws NimbusException, IOException {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String userCommand = ui.getUserCommand();
            if (userCommand.isEmpty()) {
                continue;
            }

            Command c = parse(userCommand);
            c.execute(taskList, ui);
            isExit = c.isExit();
            if (isExit) {
                storage.save(taskList);
            }
        }
    }

    public static void main(String[] args) throws NimbusException, IOException {
        new Nimbus("data/nimbus.txt").run();
    }
}
