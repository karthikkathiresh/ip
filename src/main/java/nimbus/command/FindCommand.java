package nimbus.command;

import java.util.ArrayList;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Task;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

/**
 * Represents a command to find and list all tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private final String description;

    /**
     * Constructs a FindCommand.
     *
     * @param description The keyword to search for within the task descriptions.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! Specify keyword properly!");
        }

        ArrayList<Task> matchingTasks = taskList.findTasks(description);
        ui.printListForFind(matchingTasks);
    }
}
