package seedu.duke.data;

import seedu.duke.module.Module;

public class SemModulesList extends ModuleList {
    private String semName;

    public SemModulesList(String semName) {
        this.semName = semName;
    }

    public String getSem() {
        return semName;
    }

    /**
     * Checks if the moduleName is in the semModulesList.
     * @param moduleName : name of the module to check in the ModulesList.
     * @param semModulesList : ModulesList to check if the moduleName is in.
     */
    public boolean isInList(String moduleName, SemModulesList semModulesList) {
        for (Module module: semModulesList) {
            if (module.getName().equals(moduleName)) {
                return true;
            }
        }
        return false;
    }

}
