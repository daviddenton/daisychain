package org.daisychain.source;

import static org.daisychain.source.Builders.aReference;
import static org.daisychain.source.Builders.aVariableFull;
import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class ReferenceTest {

    @Test
    public void assignTo() throws Exception {
        assertSource(aReference().assignTo(aVariableFull().reference), equalTo(readFileAsString(this, "assignment")));
    }
}