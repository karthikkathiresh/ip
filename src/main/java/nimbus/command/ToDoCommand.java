package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.TaskList;
import nimbus.tasks.ToDo;
import nimbus.ui.Ui;

/**
 * Represents a command to add a new ToDo task to the task list.
 */
public class ToDoCommand extends Command {

    private final String description;

    /**
     * Constructs a ToDoCommand.
     * @param description The details of the todo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! The description of a todo cannot be empty.");
        }

        ToDo t = new ToDo(description);
        taskList.addTask(t);
        ui.printAddedMessage(t, taskList);
    }
}
