import java.util.Scanner;

public class Main {

    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        String userCommand;
        Scanner in = new Scanner(System.in);

        MessagePrinter.printWelcomeMessage();

        userCommand = in.nextLine();

        while (true) {
            if (userCommand.equals(UserCommandParser.COMMAND_LIST)) {
                MessagePrinter.printListOfTasks(taskList);
            } else if (userCommand.startsWith(UserCommandParser.COMMAND_MARK)) {
                int listIndex = UserCommandParser.parseForMarking(userCommand);
                TaskHandler.markTaskAsDone(listIndex, taskList);
            } else if (userCommand.startsWith(UserCommandParser.COMMAND_UNMARK)) {
                int listIndex = UserCommandParser.parseForMarking(userCommand);
                TaskHandler.markTaskAsUndone(listIndex, taskList);
            } else if (userCommand.equals(UserCommandParser.COMMAND_EXIT)) {
                MessagePrinter.printGoodbyeMessage();
                return;
            } else {
                if (userCommand.startsWith(UserCommandParser.COMMAND_TODO)) {
                    TaskHandler.handleToDo(userCommand, taskList);
                } else if (userCommand.startsWith(UserCommandParser.COMMAND_DEADLINE)) {
                    TaskHandler.handleDeadline(userCommand, taskList);
                } else if (userCommand.startsWith(UserCommandParser.COMMAND_EVENT)) {
                    TaskHandler.handleEvent(userCommand, taskList);
                } else {
                    Task t = new Task(userCommand);
                    taskList.addTask(t);
                    MessagePrinter.printAddedMessage(t, taskList.getTaskCount());
                }
            }

            userCommand = in.nextLine();
        }
    }

}
