package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Task;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public class UnmarkCommand extends Command {

    private final String description;

    public UnmarkCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! Specify the task number!");
        }

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
    }
}
