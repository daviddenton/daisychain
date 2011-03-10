package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class SourcePackageTest extends ObjectMother {

    @Test
    public void source() throws Exception {
        assertSource(new SourcePackage("org.daisychain.source"), equalTo(readFileAsString(this, "full")));
    }
}