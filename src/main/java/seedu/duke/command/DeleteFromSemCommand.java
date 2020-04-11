package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.Person;
import seedu.duke.data.SemModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.InputException;
import seedu.duke.exception.RuntimeException;
import seedu.duke.exception.StorageException;
import seedu.duke.module.Grading;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;
import seedu.duke.module.Module;

public class DeleteFromSemCommand extends DeleteCommand {

    private String moduleIdentifier;
    private String semester;
    private String type;
    private String yearSemester;

    /**
     * Constructor for DeleteFromSemCommand.
     * @param moduleIdentifier The Id or the Name of the Module.
     * @param semester The Semester the module was allocated to.
     * @param type To determine if the moduleIdentifier is an Id or the Name of the module.
     */
    public DeleteFromSemCommand(String moduleIdentifier, String semester, String type) {
        this.moduleIdentifier = moduleIdentifier;
        this.semester = semester;
        this.type = type;
        setYearSemester();
    }

    public void execute(SemesterList selectedModulesList, AvailableModulesList availableModulesList)
            throws RuntimeException, StorageException, InputException {
        deleteModule(selectedModulesList);
        Ui.showDeleteFromSemMessage(String.format("Module %s has been deleted from semester %s",
                moduleIdentifier, yearSemester));

        super.execute(selectedModulesList, availableModulesList);

    }

    private void deleteModule(SemesterList selectedModulesList) throws RuntimeException, InputException {
        boolean isModuleInSem = checkModuleExistInCorrectSem(selectedModulesList);
        if (!isModuleInSem) {
            throw new RuntimeException(String.format("Module %s not found in Semester %s",
                    moduleIdentifier, semester));
        }

        SelectedModule module;
        for (SemModulesList semModulesList : selectedModulesList) {
            if (semester.equals(semModulesList.getSem())) {
                module = semModulesList.getModule(moduleIdentifier);
                boolean isModuleGradeF = module.getGrade().equals(Grading.F);
                boolean isModuleGradeCU = module.getGrade().equals(Grading.CU);
                boolean hasModuleFailed = isModuleGradeCU || isModuleGradeF;
                if (module.getDone() && !hasModuleFailed) {
                    Person.minusTotalModuleCreditCompleted(module.getModuleCredit());
                }
                semModulesList.remove(module);
                break;
            }
        }
    }

    /**
     * Converts semester (e.g. semester 5) to specific year semester string (Y3S1)
     */
    public void setYearSemester() {
        StringBuilder yearSemesterBuilder = new StringBuilder();
        int intSemester = Integer.parseInt(semester);
        yearSemesterBuilder.append("Y").append((intSemester + 1) / 2).append("S");
        if (intSemester % 2 == 0) {
            yearSemesterBuilder.append(2);
        } else {
            yearSemesterBuilder.append(1);
        }
        yearSemester = yearSemesterBuilder.toString();
    }

    private boolean checkModuleExistInCorrectSem(SemesterList moduleList) {
        for (SemModulesList sem: moduleList) {
            if (sem.getSem().equalsIgnoreCase(semester) && sem.isInList(moduleIdentifier)) {
                return true;
            }
        }
        return false;
    }
}
