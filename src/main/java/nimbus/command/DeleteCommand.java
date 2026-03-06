package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Task;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

/**
 * Represents a command to delete a task from the task list using its display index.
 */
public class DeleteCommand extends Command {

    private final String description;

    /**
     * Constructs a DeleteCommand.
     *
     * @param description The string representation of the task index to be deleted.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! Specify the task number!");
        }

        try {
            int index = Integer.parseInt(description);
            int zeroBasedIndex = index - 1;
            if (zeroBasedIndex < 0 || zeroBasedIndex >= taskList.getTaskCount()) {
                throw new NimbusException("    OOPS!!! Task number " + index + " does not exist!");
            }

            Task task = taskList.getTask(zeroBasedIndex);
            taskList.removeTask(task);
            ui.printDeletedMessage(task, taskList);
        } catch (NumberFormatException e) {
            throw new NimbusException("    OOPS!!! Please provide a valid number!");
        }
    }
}
