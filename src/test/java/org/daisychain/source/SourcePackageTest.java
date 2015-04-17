package org.daisychain.source;

import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class SourcePackageTest {

    @Test
    public void source() throws Exception {
        assertSource(new SourcePackage("org.daisychain.source"), equalTo(readFileAsString(this, "full")));
    }
}