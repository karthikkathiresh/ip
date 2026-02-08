public class UserCommandParser {

    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";

    public static int parseForMarking(String command) {
        String integerPart = command.split(" ")[1];
        return Integer.parseInt(integerPart) - 1;
    }

    public static String extractDescriptionForEvent(String command) {
        int startIndex = command.indexOf(' ') + 1;
        int endIndex = command.indexOf("/from") - 1;
        return command.substring(startIndex, endIndex);
    }

    public static String extractDescriptionForDeadline(String command) {
        int startIndex = command.indexOf(' ') + 1;
        int lastIndex = command.indexOf("/by") - 1;
        return command.substring(startIndex, lastIndex);
    }

    public static String extractDescriptionForToDo(String command) {
        int firstSpaceIndex = command.indexOf(' ');
        return command.substring(firstSpaceIndex + 1);
    }

    public static String extractDeadlineForDeadline(String command) {
        int indexOfDeadline = command.indexOf("/by") + 4;
        return command.substring(indexOfDeadline);
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
}