public class TaskHandler {

    public static final String MESSAGE_INVALID_TASK = "    Invalid task. Try again!";
    public static final String MESSAGE_MARKED_TASK = "    Nice! I've marked this task as done:";
    public static final String MESSAGE_UNMARKED_TASK = "    OK, I've marked this task as not done yet:";

    public static void markTaskAsDone(int listIndex, TaskList taskList) {
        if (listIndex >= taskList.getTaskCount()) {
            System.out.println(MESSAGE_INVALID_TASK);
        } else {
            taskList.getTask(listIndex).setIsDone(true);
            MessagePrinter.printHorizontalLine();
            System.out.println(MESSAGE_MARKED_TASK);
            System.out.println("    " + taskList.getTask(listIndex).toString());
            MessagePrinter.printHorizontalLine();
        }
    }

    public static void markTaskAsUndone(int listIndex, TaskList taskList) {
        if (listIndex >= taskList.getTaskCount()) {
            System.out.println(MESSAGE_INVALID_TASK);
        } else {
            taskList.getTask(listIndex).setIsDone(false);
            MessagePrinter.printHorizontalLine();
            System.out.println(MESSAGE_UNMARKED_TASK);
            System.out.println("    " + taskList.getTask(listIndex).getStatusIcon() + " " + taskList.getTask(listIndex).getDescription());
            MessagePrinter.printHorizontalLine();
        }
    }

    // Inside TaskHandler.java

    public static void handleToDo(String command, TaskList taskList) {
        String description = UserCommandParser.extractDescriptionForToDo(command);
        Task t = new ToDo(description);
        taskList.addTask(t);
        MessagePrinter.printAddedMessage(t, taskList.getTaskCount());
    }

    public static void handleDeadline(String command, TaskList taskList) {
        String description = UserCommandParser.extractDescriptionForDeadline(command);
        String by = UserCommandParser.extractDeadlineForDeadline(command);
        Task t = new Deadline(description, by);
        taskList.addTask(t);
        MessagePrinter.printAddedMessage(t, taskList.getTaskCount());
    }

    public static void handleEvent(String command, TaskList taskList) {
        String description = UserCommandParser.extractDescriptionForEvent(command);
        String from = UserCommandParser.extractFromForEvent(command);
        String to = UserCommandParser.extractToForEvent(command);
        Task t = new Event(description, from, to);
        taskList.addTask(t);
        MessagePrinter.printAddedMessage(t, taskList.getTaskCount());
    }
}