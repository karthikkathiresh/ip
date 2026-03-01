package nimbus.ui;

import nimbus.tasks.Task;
import nimbus.tasks.TaskList;
public class MessagePrinter {

    public static final String HORIZONTAL_LINE = "    _____________________________________________________";
    public static final String WELCOME_MESSAGE = "    Hello! I'm Nimbus\n    What can I do for you?";
    public static final String GOODBYE_MESSAGE = "    Bye. Hope to see you again soon!";
    public static final String LIST_MESSAGE = "    Here are the tasks in your list:";
    public static final String MARKED_MESSAGE = "    Nice! I've marked this task as done:";
    public static final String UNMARKED_MESSAGE = "    OK, I've marked this task as not done yet:";
    public static final String INVALID_MESSAGE = "    Invalid task index. Try again!";
    public static final String ADDED_MESSAGE = "    Got it. I've added this task:";
    public static final String DELETED_MESSAGE = "    Noted. I've removed this task:";
    public static final String MARKED_ERROR_MESSAGE = "    Task is already marked as done!";
    public static final String UNMARKED_ERROR_MESSAGE = "    Task is already marked as undone!";
    public static final String INVALID_TASK_MESSAGE = "    Invalid Task Name! Try again!";

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printWelcomeMessage() {
        printLine();
        System.out.println(WELCOME_MESSAGE);
        printLine();
    }

    public static void printGoodbyeMessage() {
        printLine();
        System.out.println(GOODBYE_MESSAGE);
        printLine();
    }

    public static void printListOfTasks(TaskList taskList) {
        MessagePrinter.printLine();
        System.out.println(LIST_MESSAGE);
        for (int index = 0; index < taskList.getTaskCount(); index++) {
            System.out.println("    " + (index + 1) + ". " + taskList.getTask(index).toString());
        }
        MessagePrinter.printLine();
    }

    public static void printAddedMessage(Task task, TaskList taskList) {
        int count = taskList.getTaskCount();
        MessagePrinter.printLine();
        System.out.println(ADDED_MESSAGE);
        System.out.println("     " + task.toString());
        System.out.println("    Now you have " + count + " tasks in the list");
        MessagePrinter.printLine();
    }

    public static void printDeletedMessage(Task task, TaskList taskList) {
        int count = taskList.getTaskCount();
        printLine();
        System.out.println(DELETED_MESSAGE);
        System.out.println("    " + task.toString());
        System.out.println("    Now you have " + count + " tasks in the list");
        printLine();
    }

    public static void printMarkedTask(Task task) {
        printLine();
        System.out.println(MARKED_MESSAGE);
        System.out.println("    " + task.toString());
        printLine();
    }

    public static void printUnmarkedTask(Task task) {
        printLine();
        System.out.println(UNMARKED_MESSAGE);
        System.out.println("    " + task.toString());
        printLine();
    }

    public static void printMarkedError() {
        printLine();
        System.out.println(MARKED_ERROR_MESSAGE);
        printLine();
    }

    public static void printUnmarkedError() {
        printLine();
        System.out.println(UNMARKED_ERROR_MESSAGE);
        printLine();
    }

    public static void printInvalidTaskMessage() {
        printLine();
        System.out.println(INVALID_TASK_MESSAGE);
        printLine();
    }
}