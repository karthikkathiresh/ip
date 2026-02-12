public class TaskHandler {

    public static void mark(int index, TaskList taskList) {
        Task task = taskList.getTask(index - 1);
        task.setIsDone(true);
        MessagePrinter.printMarkedTask(task);
    }

    public static void unmark(int index, TaskList taskList) {
        Task task = taskList.getTask(index - 1);
        task.setIsDone(false);
        MessagePrinter.printUnmarkedTask(task);
    }

    public static void handleToDo(String description, TaskList taskList) {
        ToDo t = new ToDo(description);
        taskList.addTask(t);
        MessagePrinter.printAddedMessage(t, taskList);
    }

    public static void handleDeadline(String description, TaskList taskList) {
        String desc = Parser.extractDescriptionForDeadline(description);
        String by = Parser.extractDeadline(description);
        Deadline d = new Deadline(desc, by);
        taskList.addTask(d);
        MessagePrinter.printAddedMessage(d, taskList);
    }

    public static void handleEvent(String command, TaskList taskList) {
        String description = Parser.extractDescriptionForEvent(command);
        String from = Parser.extractFrom(command);
        String to = Parser.extractTo(command);
        Event e = new Event(description, from, to);
        taskList.addTask(e);
        MessagePrinter.printAddedMessage(e, taskList);
    }
}