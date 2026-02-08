public class MessagePrinter {

    public static final String HORIZONTAL_LINE = "    _____________________________________________";
    public static final String WELCOME_MESSAGE = "    Hello! I'm Nimbus\n    What can I do for you?";
    public static final String GOODBYE_MESSAGE = "    Bye. Hope to see you again soon!";
    public static final String LIST_MESSAGE = "    Here are the tasks in your list:";

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println(WELCOME_MESSAGE);
        printHorizontalLine();
    }

    public static void printGoodbyeMessage() {
        printHorizontalLine();
        System.out.println(GOODBYE_MESSAGE);
        printHorizontalLine();
    }

    public static void printListOfTasks(TaskList taskList) {
        MessagePrinter.printHorizontalLine();
        System.out.println(LIST_MESSAGE);
        for (int index = 0; index < taskList.getTaskCount(); index++) {
            System.out.println("    " + (index + 1) + ". " + taskList.getTask(index).toString());
        }
        MessagePrinter.printHorizontalLine();
    }

    public static void printAddedMessage(Task task, int count) {
        MessagePrinter.printHorizontalLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("    Now you have " + count + " tasks in the list");
        MessagePrinter.printHorizontalLine();
    }
}