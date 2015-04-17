package org.daisychain.source;

import static org.daisychain.source.Builders.aVariableFull;
import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class InstanceTest {

    @Test
    public void callMethod() throws Exception {
        final Arguments arguments = new Arguments(aVariableFull(), aVariableFull());
        assertSource(aVariableFull().call("replaceAll", arguments.asStatements()), equalTo(readFileAsString(this, "callMethod")));
    }

    @Test
    public void value() throws Exception {
        assertSource(aVariableFull().reference, equalTo(readFileAsString(this, "value")));
    }
}