package org.daisychain.source;

import static org.daisychain.source.Builders.aFinalParameter;
import static org.daisychain.source.Builders.aNonFinalParameter;
import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class ParameterTest {

    @Test
    public void finalParameter() throws Exception {
        assertSource(aFinalParameter(), equalTo(readFileAsString(this, "final")));
    }

    @Test
    public void nonFinalParameter() throws Exception {
        assertSource(aNonFinalParameter(), equalTo(readFileAsString(this, "nonfinal")));
    }
}