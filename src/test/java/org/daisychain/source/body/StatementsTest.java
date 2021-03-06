package org.daisychain.source.body;

import static org.daisychain.source.Builders.aStatementedBodyFull;
import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class StatementsTest {

    @Test
    public void body() throws Exception {
        assertSource(aStatementedBodyFull(), equalTo(readFileAsString(this, "bodyFull")));
    }
}
