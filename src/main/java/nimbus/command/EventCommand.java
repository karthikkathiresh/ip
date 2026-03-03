package nimbus.command;

import nimbus.exceptions.NimbusException;
import nimbus.tasks.Event;
import nimbus.tasks.TaskList;
import nimbus.ui.Ui;

public class EventCommand extends Command {
    private final String details;
    private final String from;
    private final String to;

    public EventCommand(String details, String from, String to) {
        this.details = details;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws NimbusException {
        if (details.isEmpty()) {
            throw new NimbusException("    OOPS!! The description of an event cannot be empty!");
        }

        Event e = new Event(details, from, to);
        taskList.addTask(e);
        ui.printAddedMessage(e, taskList);
    }
}
