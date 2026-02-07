import java.util.Scanner;

public class Main {

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();

        line = in.nextLine();

        while (true) {
            if (line.equals("list")) {
                printListOfTasks();
            } else if (line.startsWith("mark")) {
                markTaskAsDone(line);
            } else if (line.startsWith("unmark")) {
                markTaskAsUndone(line);
            } else if (line.equals("bye") || line.equals("Bye")) {
                printGoodbyeMessage();
                return;
            } else {
                if (line.startsWith("todo")) {
                    String description = extractDescriptionForToDo(line);
                    tasks[taskCount] = new ToDo(description);
                } else if (line.startsWith("deadline")) {
                    String description = extractDescriptionForDeadline(line);
                    String deadline = extractDeadlineForDeadline(line);
                    tasks[taskCount] = new Deadline(description, deadline);
                } else if (line.startsWith("event")) {
                    String description = extractDescriptionForEvent(line);
                    String from = extractFromForEvent(line);
                    String to = extractToForEvent(line);
                    tasks[taskCount] = new Event(description, from, to);

                } else {
                    tasks[taskCount] = new Task(line);
                }
                printAddedMessage(line);
                taskCount++;
            }

            line = in.nextLine();
        }
    }

    public static String extractDescriptionForEvent(String command) {
        int startIndex = command.indexOf(' ') + 1;
        int endIndex = command.indexOf("/from") - 1;
        return command.substring(startIndex, endIndex);
    }

    public static String extractFromForEvent(String command) {
        int startIndex = command.indexOf("/from") + 5;
        int endIndex = command.indexOf("/to") - 1;
        return command.substring(startIndex, endIndex);
    }

    public static String extractToForEvent(String command) {
        int startIndex = command.indexOf("/to") + 4;
        return command.substring(startIndex);
    }

    public static String extractDescriptionForToDo(String command) {
        int firstSpaceIndex = command.indexOf(' ');
        return command.substring(firstSpaceIndex + 1);
    }

    public static String extractDescriptionForDeadline(String command) {
        int startIndex = command.indexOf(' ') + 1;
        int lastIndex = command.indexOf("/by") - 1;
        return command.substring(startIndex, lastIndex);
    }

    public static String extractDeadlineForDeadline(String command) {
        int indexOfDeadline = command.indexOf("/by") + 4;
        return command.substring(indexOfDeadline);
    }

    public static void printLine() {
        System.out.println("    _____________________________________________");
    }

    public static void printWelcomeMessage() {
        printLine();
        System.out.println("    Hello! I'm Nimbus");
        System.out.println("    What can I do for you?");
        printLine();
    }

    public static void printGoodbyeMessage() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printListOfTasks() {
        printLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("    " + (i + 1) + ". " + tasks[i].toString());
        }
        printLine();
    }

    public static void markTaskAsDone(String line) {
        String integerPart = line.split(" ")[1];
        int listIndex = Integer.parseInt(integerPart) - 1;
        if (listIndex >= taskCount) {
            System.out.println("    Invalid task. Try again!");
        } else {
            tasks[listIndex].setIsDone(true);
            printLine();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("    " + tasks[listIndex].toString());
            printLine();
        }
    }

    public static void markTaskAsUndone(String line) {
        String integerPart = line.split(" ")[1];
        int listIndex = Integer.parseInt(integerPart) - 1;
        if (listIndex >= taskCount) {
            System.out.println("    Invalid task. Try again!");
        } else {
            tasks[listIndex].setIsDone(false);
            printLine();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("    " + tasks[listIndex].getStatusIcon() + " " + tasks[listIndex].getDescription());
            printLine();
        }
    }

    public static void printAddedMessage(String task) {
        printLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + tasks[taskCount].toString());
        System.out.printf("    Now you have %d tasks in the list\n", taskCount + 1);
        printLine();
    }
}
