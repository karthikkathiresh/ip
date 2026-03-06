package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Task;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

/**
 * Represents a command to unmark a specific task, indicating it is not completed.
 */
public class UnmarkCommand extends Command {

    private final String description;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param description The string representation of the task index to be unmarked.
     */
    public UnmarkCommand(String description) {
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
                throw new NimbusException("    OOPS!!! Task number " + index + " does not exist");
            }

            Task task = taskList.getTask(zeroBasedIndex);

            if (task.getIsDone().equals(false)) {
                ui.printUnmarkedError();
            } else {
                task.setIsDone(false);
                ui.printUnmarkedTask(task);
            }
        } catch (NumberFormatException e) {
            throw new NimbusException("    OOPS!!! Please provide a valid number!");
        }
    }
}
