package nimbus;

import static nimbus.tasks.Parser.parse;

import java.io.IOException;
import java.io.File;

import nimbus.command.Command;
import nimbus.exceptions.NimbusException;
import nimbus.storage.FileReader;
import nimbus.storage.FileSaver;
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
        Command c = parse(userCommand);
        c.execute(taskList, ui);
        if (c.isExit()) {
            FileSaver.write(f, taskList);
            System.exit(0);
        }
    }

}
