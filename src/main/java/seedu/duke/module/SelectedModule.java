package seedu.duke.module;

import seedu.duke.data.ModuleList;

public class SelectedModule extends Module {
    private boolean isCompleted;
    private double grade;

    /**
     * This is Module's constructor.
     *
     * @param type             the type of module identifier.
     * @param moduleIdentifier the identifier which can be either module's name or module's id.
     * @param semester         the module's semester.
     */
    public SelectedModule(String type, String moduleIdentifier, String semester) {
        super(type, moduleIdentifier, semester);
    }

    /**
     * This is Module's constructor.
     *
     * @param module           the module from available module list.
     */
    public SelectedModule(Module module) {
        super();
        super.name = module.name;
        super.id = module.id;
        super.semester = module.semester;
        super.description = module.description;
        super.isSUable = module.isSUable;
        super.preReqModules = module.preReqModules;
        super.isNameValid = module.isNameValid;
        super.isIdValid = module.isIdValid;
        super.isDone = module.isDone;
    }

    public void markAsDone() {
        boolean isCompleted = true;
    }

    @Override
    public String toString() {
        return super.getIcon() + " " + super.toString();
    }
}
