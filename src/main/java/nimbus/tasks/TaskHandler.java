package nimbus.tasks;

import nimbus.exceptions.NimbusException;
import nimbus.ui.Ui;

public class TaskHandler {

    public static void delete(String description, TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! Specify the task number!");
        }

        int index = Integer.parseInt(description);
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= taskList.getTaskCount()) {
            throw new NimbusException("    OOPS!!! Task number " + index + " does not exist!");
        }

        Task task = taskList.getTask(zeroBasedIndex);
        taskList.removeTask(task);
        ui.printDeletedMessage(task, taskList);

    }

    public static void mark(String description, TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! Specify the task number!");
        }

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
    }

    public static void unmark(String description, TaskList taskList, Ui ui) throws NimbusException {
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

    public static void handleToDo(String description, TaskList taskList, Ui ui) throws NimbusException {

        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! The description of a todo cannot be empty.");
        }

        ToDo t = new ToDo(description);
        taskList.addTask(t);
        ui.printAddedMessage(t, taskList);
    }

    public static void handleDeadline(String description, TaskList taskList, Ui ui) throws NimbusException {
        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!! The description of a deadline cannot be empty!");
        }

        String desc = Parser.extractDescriptionForDeadline(description);
        String by = Parser.extractDeadline(description);
        Deadline d = new Deadline(desc, by);
        taskList.addTask(d);
        ui.printAddedMessage(d, taskList);
    }

    public static void handleEvent(String command, TaskList taskList, Ui ui) throws NimbusException {
        if (command.isEmpty()) {
            throw new NimbusException("    OOPS!! The description of an event cannot be empty!");
        }

        String description = Parser.extractDescriptionForEvent(command);
        String from = Parser.extractFrom(command);
        String to = Parser.extractTo(command);
        Event e = new Event(description, from, to);
        taskList.addTask(e);
        ui.printAddedMessage(e, taskList);
    }
}