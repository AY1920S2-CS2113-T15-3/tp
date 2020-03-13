package seedu.duke;

import java.util.Scanner;

import seedu.duke.command.Command;
import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.Ui;


public class Duke {

    private static AvailableModulesList availableModulesList;
    private static SelectedModulesList selectedModulesList;
    private static Ui ui;

    /**
     * Instantiate all required classes.
     */
    public Duke() {
        ui = new Ui();
        availableModulesList = new AvailableModulesList();
        selectedModulesList = new SelectedModulesList();
    }

    /**
     * Main program to run.
     */
    public void run() {
        ui.greetUser();
        String fullCommand;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        do {
            fullCommand = in.nextLine();
            Command command = Parser.parse(fullCommand);
            command.execute(selectedModulesList, availableModulesList);
            isExit = command.isExit();
        } while (!isExit);
        ui.greetFarewell();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
