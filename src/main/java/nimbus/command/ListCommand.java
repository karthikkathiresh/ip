package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        ui.printListOfTasks(taskList);
    }
}
