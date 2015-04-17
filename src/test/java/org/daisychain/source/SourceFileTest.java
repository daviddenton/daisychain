package org.daisychain.source;

import org.junit.Test;

import static org.daisychain.source.Builders.aClassFull;
import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class SourceFileTest {

    @Test
    public void full() throws Exception {
        final SourceFile builder = new SourceFile(new SourcePackage(this.getClass().getPackage()), aClassFull());
        assertSource(builder, equalTo(readFileAsString(this, "full")));
    }
}