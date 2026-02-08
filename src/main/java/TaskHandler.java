public class TaskHandler {

    private static boolean isValidIndex(int listIndex, TaskList taskList) {
        return listIndex >= 0 && listIndex < taskList.getTaskCount();
    }

    public static void markTaskAsDone(int listIndex, TaskList taskList) {
        if (isValidIndex(listIndex, taskList)) {
            Task task = taskList.getTask(listIndex);
            task.setIsDone(true);
            MessagePrinter.printMarkedTask(task);
        } else {
            MessagePrinter.printError();
        }
    }

    public static void markTaskAsUndone(int listIndex, TaskList taskList) {
        if (isValidIndex(listIndex, taskList)) {
            Task task = taskList.getTask(listIndex);
            task.setIsDone(false);
            MessagePrinter.printUnmarkedTask(task);
        } else {
            MessagePrinter.printError();
        }
    }

    private static void addTaskAndPrint(Task task, TaskList taskList) {
        taskList.addTask(task);
        MessagePrinter.printAddedMessage(task, taskList.getTaskCount());
    }

    public static void handleToDo(String command, TaskList taskList) {
        String description = UserCommandParser.extractDescriptionForToDo(command);
        ToDo t = new ToDo(description);
        addTaskAndPrint(t, taskList);
    }

    public static void handleDeadline(String command, TaskList taskList) {
        String description = UserCommandParser.extractDescriptionForDeadline(command);
        String by = UserCommandParser.extractDeadlineForDeadline(command);
        Deadline d = new Deadline(description, by);
        addTaskAndPrint(d, taskList);
    }

    public static void handleEvent(String command, TaskList taskList) {
        String description = UserCommandParser.extractDescriptionForEvent(command);
        String from = UserCommandParser.extractFromForEvent(command);
        String to = UserCommandParser.extractToForEvent(command);
        Event e = new Event(description, from, to);
        addTaskAndPrint(e, taskList);
    }
}