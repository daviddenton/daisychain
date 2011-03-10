package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class GeneratedClassTest extends ObjectMother {

    @Test
    public void classBlank() throws Exception {
        assertSource(aClassBlank(), equalTo(readFileAsString(this, "classBlank")));
    }

    @Test
    public void classFull() throws Exception {
        assertSource(aClassFull(), equalTo(readFileAsString(this, "classFull")));
    }
}
