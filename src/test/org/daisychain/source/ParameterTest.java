package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class ParameterTest extends ObjectMother {

    @Test
    public void finalParameter() throws Exception {
        assertSource(aFinalParameter(), equalTo(readFileAsString(this, "final")));
    }

    @Test
    public void nonFinalParameter() throws Exception {
        assertSource(aNonFinalParameter(), equalTo(readFileAsString(this, "nonfinal")));
    }
}