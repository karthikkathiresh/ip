package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Task;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public class FindCommand extends Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! Specify keyword properly!");
        }

        ui.printListForFind(taskList, description);
    }
}
