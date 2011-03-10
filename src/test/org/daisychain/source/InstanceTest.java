package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class InstanceTest extends ObjectMother {

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