package org.daisychain.source;

import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class AnnotationTest extends ObjectMother {

    @Test
    public void blank() throws Exception {
        assertSource(anAnnotationBlank(), equalTo(readFileAsString(this, "blank")));
    }

    @Test
    public void full() throws Exception {
        assertSource(anAnnotationFull(), equalTo(readFileAsString(this, "full")));
    }


    
}