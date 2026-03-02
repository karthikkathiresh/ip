package nimbus.ui;

import java.io.PrintStream;
import java.util.Scanner;

import nimbus.tasks.Task;
import nimbus.tasks.TaskList;

public class Ui {
    public static final String INDENT = "    ";
    public static final String HORIZONTAL_LINE = INDENT + "_____________________________________________________";
    public static final String WELCOME_MESSAGE = INDENT + "Hello! I'm Nimbus\n    What can I do for you?";
    public static final String GOODBYE_MESSAGE = INDENT + "Bye. Hope to see you again soon!";
    public static final String LIST_MESSAGE = INDENT + "Here are the tasks in your list:";
    public static final String MARKED_MESSAGE = INDENT + "Nice! I've marked this task as done:";
    public static final String UNMARKED_MESSAGE = INDENT + "OK, I've marked this task as not done yet:";
    public static final String INVALID_MESSAGE = INDENT + "Invalid task index. Try again!";
    public static final String ADDED_MESSAGE = INDENT + "Got it. I've added this task:";
    public static final String DELETED_MESSAGE = INDENT + "Noted. I've removed this task:";
    public static final String MARKED_ERROR_MESSAGE = INDENT + "Task is already marked as done!";
    public static final String UNMARKED_ERROR_MESSAGE = INDENT + "Task is already marked as undone!";
    public static final String INVALID_TASK_MESSAGE = INDENT + "Invalid Task Name! Try again!";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = new PrintStream(System.out);
    }

    public String getUserCommand() {
        out.print("    Enter Command: ");
        return in.nextLine().trim();
    }

    public void printToScreen(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void printWelcomeMessage() {
        printToScreen(
                HORIZONTAL_LINE,
                WELCOME_MESSAGE,
                HORIZONTAL_LINE
        );
    }

    public void printGoodbyeMessage() {
        printToScreen(
                HORIZONTAL_LINE,
                GOODBYE_MESSAGE,
                HORIZONTAL_LINE
        );
    }

    public void printListOfTasks(TaskList taskList) {
        java.util.ArrayList<String> messages = new java.util.ArrayList<>();
        messages.add(HORIZONTAL_LINE);
        messages.add(LIST_MESSAGE);
        for (int index = 0; index < taskList.getTaskCount(); index++) {
            messages.add(INDENT + (index + 1) + ". " + taskList.getTask(index).toString());
        }
        messages.add(HORIZONTAL_LINE);
        printToScreen(messages.toArray(new String[0]));
    }

    public void printAddedMessage(Task task, TaskList taskList) {
        int count = taskList.getTaskCount();
        String countMessage = "Now you have " + count + " tasks in the list";

        printToScreen(
                HORIZONTAL_LINE,
                ADDED_MESSAGE,
                INDENT + task.toString(),
                INDENT + countMessage,
                HORIZONTAL_LINE
        );
    }

    public void printDeletedMessage(Task task, TaskList taskList) {
        int count = taskList.getTaskCount();
        String countMessage = "Now you have " + count + " tasks in the list";

        printToScreen(
                HORIZONTAL_LINE,
                DELETED_MESSAGE,
                INDENT + task.toString(),
                INDENT + countMessage,
                HORIZONTAL_LINE
        );
    }

    public void printMarkedTask(Task task) {
        printToScreen(
                HORIZONTAL_LINE,
                MARKED_MESSAGE,
                INDENT + task.toString(),
                HORIZONTAL_LINE
        );
    }

    public void printUnmarkedTask(Task task) {
        printToScreen(
                HORIZONTAL_LINE,
                UNMARKED_MESSAGE,
                INDENT + task.toString(),
                HORIZONTAL_LINE
        );
    }

    public void printMarkedError() {
        printToScreen(
                HORIZONTAL_LINE,
                MARKED_ERROR_MESSAGE,
                HORIZONTAL_LINE
        );
    }

    public void printUnmarkedError() {
        printToScreen(
                HORIZONTAL_LINE,
                UNMARKED_ERROR_MESSAGE,
                HORIZONTAL_LINE
        );
    }

    public void printInvalidTaskMessage() {
        printToScreen(
                HORIZONTAL_LINE,
                INVALID_TASK_MESSAGE,
                HORIZONTAL_LINE
        );
    }
}