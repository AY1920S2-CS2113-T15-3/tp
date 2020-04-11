package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.data.Person;
import seedu.duke.data.SemModulesList;
import seedu.duke.exception.InputException;
import seedu.duke.exception.RuntimeException;
import seedu.duke.exception.StorageException;
import seedu.duke.module.Grading;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;

public class MarkAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private String description;
    private Grading grade;

    /**
     * marks the module in a semester in the selectedList as done.
     * @param description : name of the module or id of the module that the user wants to mark as done.
     * @param grade       : Grading awarded for the module.
     */
    public MarkAsDoneCommand(String description, Grading grade) {
        this.description = description;
        this.grade = grade;
    }

    @Override
    public void execute(SemesterList semesterList, AvailableModulesList availableModulesList)
            throws RuntimeException, StorageException, InputException {
        markAsDoneCommand(semesterList);
        Ui.showDoneMessage();
        super.execute(semesterList, availableModulesList);
    }

    private void markAsDoneCommand(SemesterList semesterList) throws RuntimeException {
        for (SemModulesList sem: semesterList) {
            for (SelectedModule module: sem) {
                if (module.getName().equals(description) || module.getId().equals(description)) {
                    if (grade != Grading.F && grade != Grading.CU && !module.getDone()) {
                        Person.addTotalModuleCreditCompleted(module.getModuleCredit());
                    }
                    module.setAsDone(grade);
                    return;
                }
            }
        }
        throw new RuntimeException("module not found");
    }
}
