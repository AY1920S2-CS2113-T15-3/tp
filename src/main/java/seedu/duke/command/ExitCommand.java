package seedu.duke.command;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public boolean isExit() {
        return true;
    }
}
