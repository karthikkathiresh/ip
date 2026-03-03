package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.TaskList;
import nimbus.tasks.ToDo;
import nimbus.ui.Ui;

public class ToDoCommand extends Command {

    private final String description;

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
