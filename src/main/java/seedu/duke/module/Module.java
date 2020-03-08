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

    /**
     * Constructor for Module.
     * @param id module code/id, eg: "CS2113"
     * @param name module name, eg: "Software Engineering & Object-Oriented Programming"
     * @param preReqMods list of modules that are prerequisites
     */

    public Module(String id, String name, String... preReqMods) {
        this.id = id;
        this.name = name;
        boolean isLevel1000Mod = id.charAt(2) == '2'; // will run into error when module code has 3 letters instead of 2
        boolean hasNoPreReq = preReqMods.length == 0;
        if (isLevel1000Mod || hasNoPreReq) {
            isSUable = true;
        } else {
            isSUable = false;
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
        return returnString + " | Semester: " + semester;
    }
}
