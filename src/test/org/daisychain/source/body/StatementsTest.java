package org.daisychain.source.body;

import org.daisychain.source.ObjectMother;
import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class StatementsTest extends ObjectMother {

    @Test
    public void body() throws Exception {
        assertSource(aStatementedBodyFull(), equalTo(readFileAsString(this, "bodyFull")));
    }
}
