package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class ReferenceTest extends ObjectMother {

    @Test
    public void assignTo() throws Exception {
        assertSource(aReference().assignTo(aVariableFull().reference), equalTo(readFileAsString(this, "assignment")));
    }
}