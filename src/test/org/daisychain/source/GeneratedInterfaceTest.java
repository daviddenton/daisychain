package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class GeneratedInterfaceTest extends ObjectMother {

    @Test
    public void interfaceBlank() throws Exception {
        assertSource(anInterfaceBlank(), equalTo(readFileAsString(this, "interfaceBlank")));
    }

    @Test
    public void interfaceFull() throws Exception {
        assertSource(anInterfaceFull(), equalTo(readFileAsString(this, "interfaceFull")));
    }

}