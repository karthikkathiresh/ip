package nimbus.tasks;

import nimbus.command.Command;
import nimbus.command.DeadlineCommand;
import nimbus.command.DeleteCommand;
import nimbus.command.EventCommand;
import nimbus.command.ExitCommand;
import nimbus.command.ListCommand;
import nimbus.command.MarkCommand;
import nimbus.command.ToDoCommand;
import nimbus.command.UnmarkCommand;
import nimbus.exceptions.NimbusException;

public class Parser {

    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    public static final String DELIMITED_FROM = "/from";
    public static final String DELIMITER_BY = "/by";
    public static final String DELIMITED_TO = "/to";

    public static String extractDescriptionForDeadline(String command) throws NimbusException {
        int byIndex = command.indexOf(DELIMITER_BY);

        if (byIndex == -1) {
            throw new NimbusException("    OOPS!! A deadline must have a '/by' time");
        }

        String description = command.substring(0, byIndex).trim();

        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!! The description of a deadline cannot be empty!");
        }

        return description;
    }

    public static String extractDeadline(String command) throws NimbusException {
        int byIndex = command.indexOf(DELIMITER_BY);

        if (byIndex == -1) {
            throw new NimbusException("    OOPS! Missing '/by' flag");
        }

        if (byIndex + 3 >= command.length()) {
            throw new NimbusException("    OOPS!! The deadline time cannot be empty.");
        }

        return command.substring(byIndex + 3).trim();
    }

    public static String extractDescriptionForEvent(String command) throws NimbusException {
        int fromIndex = command.indexOf(DELIMITED_FROM);

        if (fromIndex == -1) {
            throw new NimbusException("    OOPS!!! An event must have a 'from' time");
        }

        String description = command.substring(0, fromIndex).trim();

        if (description.isEmpty()) {
            throw new NimbusException("    OOPS!!! The description of an event cannot be empty.");
        }

        return description;
    }

    public static String extractFrom(String command) throws NimbusException {
        int fromIndex = command.indexOf(DELIMITED_FROM);
        int toIndex = command.indexOf(DELIMITED_TO);

        if (fromIndex == -1 || toIndex == -1) {
            throw new NimbusException("    OOPS!!! Event format: event <desc> /from <start> /to <end>");
        }

        if (fromIndex + 5 >= toIndex) {
            throw new NimbusException("    OOPS!!! The '/from' time cannot be empty.");
        }
        return command.substring(fromIndex + 5, toIndex).trim();
    }

    public static String extractTo(String command) throws NimbusException {
        int toIndex = command.indexOf(DELIMITED_TO);

        if (toIndex == -1) {
            throw new NimbusException("    OOPS!!! Missing '/to' flag.");
        }

        if (toIndex + 3 >= command.length()) {
            throw new NimbusException("    OOPS!!! The '/to' time cannot be empty.");
        }

        return command.substring(toIndex + 3).trim();
    }

    public static Command parse(String userCommand) throws NimbusException {
        String[] parts = userCommand.split(" ", 2);
        String commandWord = parts[0];
        String description = (parts.length > 1) ? parts[1].trim() : "";

        switch (commandWord) {

        case COMMAND_LIST:
            return new ListCommand();

        case COMMAND_DELETE:
            return new DeleteCommand(description);

        case COMMAND_MARK:
            return new MarkCommand(description);

        case COMMAND_UNMARK:
            return new UnmarkCommand(description);

        case COMMAND_TODO:
            return new ToDoCommand(description);

        case COMMAND_DEADLINE:
            String descriptionDeadline = extractDescriptionForDeadline(description);
            String by = extractDeadline(description);
            return new DeadlineCommand(descriptionDeadline, by);

        case COMMAND_EVENT:
            String descriptionEvent = extractDescriptionForEvent(description);
            String from = extractFrom(description);
            String to = extractTo(description);
            return new EventCommand(descriptionEvent, from, to);

        case COMMAND_EXIT:
            return new ExitCommand();

        default:
            throw new NimbusException("    Invalid task name. Try again!");
        }
    }
}