package org.daisychain.source;

import org.junit.Test;

import static org.daisychain.source.Builders.anInterfaceBlank;
import static org.daisychain.source.Builders.anInterfaceFull;
import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class GeneratedInterfaceTest {

    @Test
    public void interfaceBlank() throws Exception {
        assertSource(anInterfaceBlank(), equalTo(readFileAsString(this, "interfaceBlank")));
    }

    @Test
    public void interfaceFull() throws Exception {
        assertSource(anInterfaceFull(), equalTo(readFileAsString(this, "interfaceFull")));
    }

}