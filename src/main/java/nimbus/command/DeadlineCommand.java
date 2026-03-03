package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Deadline;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public class DeadlineCommand extends Command {

    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!! The description of a deadline cannot be empty!");
        }

        Deadline d = new Deadline(description, by);
        taskList.addTask(d);
        ui.printAddedMessage(d, taskList);
    }
}
