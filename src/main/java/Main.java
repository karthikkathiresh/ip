import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();

        Task[] tasks = new Task[100];
        int taskCount = 0;

        line = in.nextLine();

        while (true) {
            if (line.equals("list")) {
                printLine();
                System.out.println("    Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i].toString());
                }
                printLine();
            } else if (line.equals("bye") || line.equals("Bye")) {
                printGoodbyeMessage();
                return;
            } else {
                tasks[taskCount] = new Task(line);
                printAddedMessage(line);
                taskCount++;
            }

            line = in.nextLine();
        }
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

    public static void printAddedMessage(String task) {
        printLine();
        System.out.println("    added: " + task);
        printLine();
    }

}
