package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class SourceFileTest extends ObjectMother {

    @Test
    public void full() throws Exception {
        final SourceFile builder = new SourceFile(new SourcePackage(this.getClass().getPackage()), aClassFull());
        assertSource(builder, equalTo(readFileAsString(this, "full")));
    }
}