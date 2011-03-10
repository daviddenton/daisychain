package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class InterfaceMethodTest extends ObjectMother {

    @Test
    public void interfaceMethodBlank() throws Exception {
        assertSource(anInterfaceMethodBlank(), equalTo(readFileAsString(this, "interfaceMethodBlank")));
    }

    @Test
    public void interfaceMethodFull() throws Exception {
        assertSource(anInterfaceMethodFull(), equalTo(readFileAsString(this, "interfaceMethodFull")));
    }
}