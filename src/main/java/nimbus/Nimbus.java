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

    public static TaskList taskList;
    private static Ui ui;
    private static Storage storage;

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

    public static void main(String[] args) {

        new Nimbus("data/nimbus.txt");

        String userCommand;
        ui.printWelcomeMessage();

        while (true) {
            userCommand = ui.getUserCommand();
            if (userCommand.isEmpty()) {
                continue;
            }

            try {
                run(userCommand);
            } catch (NimbusException | IOException e) {
                Ui.printLine();
                System.out.println(e.getMessage());
                Ui.printLine();
            }
        }
    }

    private static void run(String userCommand) throws NimbusException, IOException {
        Command c = parse(userCommand);
        c.execute(taskList, ui);
        if (c.isExit()) {
            storage.save(taskList);
            System.exit(0);
        }
    }

}
