package org.daisychain.source;

import static org.daisychain.source.Builders.anAnnotationBlank;
import static org.daisychain.source.Builders.anAnnotationFull;
import static org.daisychain.test.TestHelper.assertSource;
import static org.daisychain.test.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class AnnotationTest {

    @Test
    public void blank() throws Exception {
        assertSource(anAnnotationBlank(), equalTo(readFileAsString(this, "blank")));
    }

    @Test
    public void full() throws Exception {
        assertSource(anAnnotationFull(), equalTo(readFileAsString(this, "full")));
    }
}