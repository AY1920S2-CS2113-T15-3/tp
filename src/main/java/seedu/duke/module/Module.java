package seedu.duke.module;

import seedu.duke.data.ModuleList;

public class Module {
    protected String name;
    protected String id;
    protected String semester;
    protected String description;
    protected Boolean isSUable;
    protected ModuleList preReqModules;
    protected boolean isNameValid;
    protected boolean isIdValid;


    /**
     * This is Module's constructor.
     * @param type the type of module identifier.
     * @param moduleIdentifier the identifier which can be either module's name or module's id.
     * @param semester the module's semester.
     */
    public Module(String type, String moduleIdentifier, String semester) {
        this.isNameValid = type.equals("name");
        this.isIdValid = type.equals("id");
        this.semester = semester;
        if (isNameValid) {
            this.name = moduleIdentifier;
        } else if (isIdValid) {
            this.id = moduleIdentifier;
        }
    }

    @Override
    public String toString() {
        String returnString = null;
        if (isIdValid && isNameValid) {
            returnString = "ID: " + id + " Name: " + name;
        } else if (isNameValid) {
            returnString = "Name: " + name;
        } else if (isIdValid) {
            returnString = "ID: " + id;
        }
        return returnString + " | Sem: " + semester;
    }

    public String getSem() {
        return semester;
    }
}
