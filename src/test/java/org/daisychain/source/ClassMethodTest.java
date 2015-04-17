package org.daisychain.source;

import static org.daisychain.source.Builders.aMethodBlank;
import static org.daisychain.source.Builders.aMethodFull;
import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class ClassMethodTest {

    @Test
    public void methodBlank() throws Exception {
        assertSource(aMethodBlank(), equalTo(readFileAsString(this, "methodBlank")));
    }

    @Test
    public void methodFull() throws Exception {
        assertSource(aMethodFull(), equalTo(readFileAsString(this, "methodFull")));
    }
}