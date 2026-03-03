package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws NimbusException;
}
