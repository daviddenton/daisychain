package org.daisychain.source;

import static junit.framework.Assert.assertTrue;
import static org.daisychain.source.ExistingClass.existingClass;
import static org.daisychain.source.Builders.aVariableFull;
import static org.daisychain.source.Builders.concreteClass;
import static org.daisychain.source.Builders.concreteClassFull;
import static org.daisychain.util.TestHelper.assertSource;
import static org.daisychain.util.TestHelper.readFileAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

public class ExistingClassTest {

    @Test
    public void callMethod() throws Exception {
        final Arguments arguments = new Arguments(aVariableFull());
        assertSource(concreteClass().call("valueOf", arguments.asStatements()), equalTo(readFileAsString(this, "callMethod")));
    }



    @Test
    public void aConcreteClassDefUsage() throws Exception {
        assertSource(concreteClass(), equalTo(readFileAsString(this, "aConcreteClassDefUsage")));
    }

    @Test
    public void aConcreteClassDef() throws Exception {
        assertSource(concreteClass(), equalTo(readFileAsString(this, "aConcreteClassDef")));
    }

    @Test
    public void aConcreteClassDefFull() throws Exception {
        assertSource(concreteClassFull(), equalTo(readFileAsString(this, "aConcreteClassDefFull")));
    }

    @Test
    public void implementsInterface() throws Exception {
        assertTrue(existingClass(String.class).implementsInterface(CharSequence.class));
    }

    @Test
    public void implementsInterfaceWhenClassIsAnInterfaceAlready() throws Exception {
        assertTrue(existingClass(CharSequence.class).implementsInterface(CharSequence.class));
    }

}