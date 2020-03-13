package seedu.duke.module;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleTest {

    @Test
    public void testStringConversion() {
        assertEquals("ID: CS2113 | Sem: 19/20 Sem2", new Module("id","CS2113","19/20 Sem2").toString());
    }

    @Test
    public void getSem_newModule_success() {
        assertEquals("19/20 Sem2", new Module("id","CS2113","19/20 Sem2").getSem());
    }
}
