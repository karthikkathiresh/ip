package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
