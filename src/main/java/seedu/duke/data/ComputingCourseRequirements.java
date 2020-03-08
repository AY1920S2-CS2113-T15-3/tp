package seedu.duke.data;

import seedu.duke.module.Module;

/**
 * Contains a list of the modules required for a student in the computing course to clear.
 */

public class ComputingCourseRequirements extends CourseRequirements {
    private static ModuleList requiredModules;

    /**
     * Constructor for ComputingCourseRequirements.
     */

    public ComputingCourseRequirements() {
        // dk how to create a list of modules efficiently ah, please help lol
        requiredModules.add(new Module("CS1010", "Programming Methodology I"));
        requiredModules.add(new Module("CS1231", "Discrete Structures"));
        requiredModules.add(new Module("CS2030", "Programming Methodology II", "CS1010")); // it's supposed to use the other constructor, but it's using this one. not sure how solve
    }
}
