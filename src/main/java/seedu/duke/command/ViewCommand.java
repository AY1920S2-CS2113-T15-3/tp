package seedu.duke.command;

import seedu.duke.data.ModuleList;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;
import seedu.duke.ui.Ui;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String VIEW_COMPULSORY_MODULES = "cm";
    public static final String VIEW_DONE_MODULES = "dm";
    public static final String VIEW_MODULE_PLAN = "mp";
    public static final String VIEW_SPECIFIC_MODULE = "sm";
    private String viewTaskType;
    private String moduleToBeViewed;

    public ViewCommand(String viewTaskType) {
        this.viewTaskType = viewTaskType;
    }

    public ViewCommand(String viewTaskType, String moduleIdentifier) {
        this.viewTaskType = viewTaskType;
        this.moduleToBeViewed = moduleIdentifier;
    }

    @Override
    public void execute(SelectedModulesList selectedModulesList, ModuleList availableModulesList) {
        switch (viewTaskType) {
        case VIEW_MODULE_PLAN:
            viewModulePlan(selectedModulesList);
            break;
        case VIEW_DONE_MODULES:
            viewDoneModules(selectedModulesList);
            break;
        default:
            return;
        }
    }

    /**
     * Prints the user's module plan.
     * @param moduleList user's module list.
     */
    private void viewModulePlan(SelectedModulesList moduleList) {
        StringBuilder viewList = new StringBuilder();
        for (SemModulesList sem: moduleList) {
            viewList.append(sem.getSem()).append(System.lineSeparator());
            for (Module selectedModule: sem) {
                int index = sem.indexOf(selectedModule) + 1;
                viewList.append(index).append(".")
                        .append(selectedModule.toString())
                        .append(System.lineSeparator());
            }
            viewList.append(System.lineSeparator());
        }
        Ui.showViewMessage(viewList.toString().trim());
    }

    private void viewDoneModules(SelectedModulesList moduleList) {
        StringBuilder viewList = new StringBuilder();
        for (SemModulesList sem: moduleList) {
            viewList.append(sem.getSem()).append(System.lineSeparator());
            for (Module selectedModule: sem) {
                int index = sem.indexOf(selectedModule) + 1;
                if (selectedModule.getDone()) {
                    viewList.append(index).append(".")
                            .append(selectedModule.toString())
                            .append(System.lineSeparator());

                }
            }
            viewList.append(System.lineSeparator());
        }
        Ui.showViewDoneMessage(viewList.toString().trim());
    }
}
