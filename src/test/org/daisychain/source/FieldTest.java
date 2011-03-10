package org.daisychain.source;

import static org.daisychain.source.Builders.aFieldBlank;
import static org.daisychain.source.Builders.aFieldFull;
import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class FieldTest {

    @Test
    public void blank() throws Exception {
        assertSource(aFieldBlank(), equalTo(readFileAsString(this, "blank")));
    }

    @Test
    public void full() throws Exception {
        assertSource(aFieldFull(), equalTo(readFileAsString(this, "full")));
    }


}