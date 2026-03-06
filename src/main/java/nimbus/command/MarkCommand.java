package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Task;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

/**
 * Represents a command to mark a specific task in the task list as completed.
 */
public class MarkCommand extends Command {

    private final String description;

    /**
     * Constructs a MarkCommand.
     *
     * @param description The string representation of the task index to be marked as done.
     */
    public MarkCommand(String description) {
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
            if (task.getIsDone().equals(true)) {
                ui.printMarkedError();
            } else {
                task.setIsDone(true);
                ui.printMarkedTask(task);
            }

        } catch (NumberFormatException e) {
            throw new NimbusException("    OOPS!!! Please provide a valid number!");
        }




    }
}
