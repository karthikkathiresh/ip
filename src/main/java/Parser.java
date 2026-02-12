public class Parser {

    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";

    public static final String DELIMITED_FROM = "/from";
    public static final String DELIMITER_BY = "/by";
    public static final String DELIMITED_TO = "/to";

    public static String extractDescriptionForDeadline(String command) {
        int lastIndex = command.indexOf(DELIMITER_BY) - 1;
        return command.substring(0, lastIndex);
    }

    public static String extractDeadline(String command) {
        int index = command.indexOf(DELIMITER_BY) + 4;
        return command.substring(index);
    }

    public static String extractDescriptionForEvent(String command) {
        int lastIndex = command.indexOf(DELIMITED_FROM) - 1;
        return command.substring(0, lastIndex);
    }

    public static String extractFrom(String command) {
        int startIndex = command.indexOf(DELIMITED_FROM) + 5;
        int endIndex = command.indexOf(DELIMITED_TO) - 1;
        return command.substring(startIndex, endIndex);
    }

    public static String extractTo(String command) {
        int startIndex = command.indexOf(DELIMITED_TO) + 4;
        return command.substring(startIndex);
    }
}