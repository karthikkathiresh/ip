package nimbus.tasks;

import nimbus.exceptions.NimbusException;
import nimbus.ui.MessagePrinter;

public class TaskHandler {

    public static void delete(int index, TaskList taskList) throws NimbusException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= taskList.getTaskCount()) {
            throw new NimbusException("    OOPS!!! Task number " + index + " does not exist!");
        }

        Task task = taskList.getTask(zeroBasedIndex);
        taskList.removeTask(task);
        MessagePrinter.printDeletedMessage(task, taskList);

    }

    public static void mark(int index, TaskList taskList) throws NimbusException {
        int zeroBasedIndex = index - 1;
        if (zeroBasedIndex < 0 || zeroBasedIndex >= taskList.getTaskCount()) {
            throw new NimbusException("    OOPS!!! Task number " + index + " does not exist!");
        }

        Task task = taskList.getTask(zeroBasedIndex);

        if (task.getIsDone().equals(true)) {
            MessagePrinter.printMarkedError();
        } else {
            task.setIsDone(true);
            MessagePrinter.printMarkedTask(task);
        }
    }

    public static void unmark(int index, TaskList taskList) throws NimbusException {
        int zeroBasedIndex = index - 1;

        if (zeroBasedIndex < 0 || zeroBasedIndex >= taskList.getTaskCount()) {
            throw new NimbusException("    OOPS!!! Task number " + index + " does not exist");
        }

        Task task = taskList.getTask(zeroBasedIndex);

        if (task.getIsDone().equals(false)) {
            MessagePrinter.printUnmarkedError();
        } else {
            task.setIsDone(false);
            MessagePrinter.printUnmarkedTask(task);
        }
    }

    public static void handleToDo(String description, TaskList taskList) throws NimbusException {

        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! The description of a todo cannot be empty.");
        }

        ToDo t = new ToDo(description);
        taskList.addTask(t);
        MessagePrinter.printAddedMessage(t, taskList);
    }

    public static void handleDeadline(String description, TaskList taskList) throws NimbusException {
        String desc = Parser.extractDescriptionForDeadline(description);
        String by = Parser.extractDeadline(description);
        Deadline d = new Deadline(desc, by);
        taskList.addTask(d);
        MessagePrinter.printAddedMessage(d, taskList);
    }

    public static void handleEvent(String command, TaskList taskList) throws NimbusException {
        String description = Parser.extractDescriptionForEvent(command);
        String from = Parser.extractFrom(command);
        String to = Parser.extractTo(command);
        Event e = new Event(description, from, to);
        taskList.addTask(e);
        MessagePrinter.printAddedMessage(e, taskList);
    }
}