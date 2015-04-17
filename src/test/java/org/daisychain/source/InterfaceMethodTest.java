package org.daisychain.source;

import static org.daisychain.source.Builders.anInterfaceMethodBlank;
import static org.daisychain.source.Builders.anInterfaceMethodFull;
import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class InterfaceMethodTest {

    @Test
    public void interfaceMethodBlank() throws Exception {
        assertSource(anInterfaceMethodBlank(), equalTo(readFileAsString(this, "interfaceMethodBlank")));
    }

    @Test
    public void interfaceMethodFull() throws Exception {
        assertSource(anInterfaceMethodFull(), equalTo(readFileAsString(this, "interfaceMethodFull")));
    }
}