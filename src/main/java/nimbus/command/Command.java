package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

/**
 * Represents an executable command from the user.
 * This is the abstract base class for all specific command types in the application.
 */
public abstract class Command {

    /**
     * Indicates whether this command should terminate the application.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the specific logic of the command.
     *
     * @param taskList The current list of tasks.
     * @param ui The user interface handling inputs and outputs.
     * @throws NimbusException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws NimbusException;
}
