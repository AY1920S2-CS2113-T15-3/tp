package seedu.duke.command;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.module.NewModule;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;
import seedu.duke.data.SelectedModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.module.Module;

public class AddToSemCommand extends Command {

    public static final String COMMAND_WORD = "addtosem";
    private Module module;
    private SelectedModule selectedModule;

    public AddToSemCommand(Module module) {
        this.module = module;
        this.selectedModule = new SelectedModule(module);
        checkAvailableModulesList(selectedModule);
    }

    @Override
    public void execute(SelectedModulesList selectedModulesList, AvailableModulesList availableModulesList) {
        addModule(selectedModulesList);
        Ui.showAddedToSemMessage(module.toString());
    }

    private void addModule(SelectedModulesList moduleList) {
        boolean isModuleExist = checkModuleExist(moduleList);
        if (isModuleExist) {
            return;
        }

        for (SemModulesList sem: moduleList) {
            if (sem.getSem().equals(selectedModule.getSem())) {
                sem.add(selectedModule);
                return;
            }
        }
        SemModulesList sem = new SemModulesList(selectedModule.getSem());
        sem.add(selectedModule);
        moduleList.add(sem);
    }

    private void checkAvailableModulesList(SelectedModule selectedModule) {
        for (Module availableModule: AvailableModulesList.availableModulesList) {
            boolean isSameName = availableModule.getName().equals(selectedModule.getName());
            boolean isSameId = availableModule.getId().equals(selectedModule.getId());
            if (isSameName || isSameId) {
                selectedModule.setModuleConfig(availableModule);
            }
        }
    }

    private boolean checkModuleExist(SelectedModulesList moduleList) {
        for (SemModulesList sem: moduleList) {
            if (sem.isInList(selectedModule.getName(), sem)) {
                return true;
            }
        }
        return false;
    }
}
