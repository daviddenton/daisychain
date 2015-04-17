package org.daisychain.source;

import static junit.framework.Assert.assertTrue;
import static org.daisychain.source.DaisyChain.a;
import org.junit.Test;

public class MutableClassTest {

    @Test
    public void implementsInterface() throws Exception {
        GeneratedClass aClass = a(Modifier.Public)
                .generatedClass("AnotherClass")
                .implementing(ExistingClass.existingClass(CharSequence.class)).build();
        assertTrue(aClass.implementsInterface(CharSequence.class));
    }
}
