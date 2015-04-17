package org.daisychain.test;

import org.apache.commons.io.IOUtils;
import org.daisychain.source.HasImports;
import org.daisychain.source.MutableClass;
import org.daisychain.source.SourceGenerator;
import org.daisychain.source.body.Statement;
import org.daisychain.source.util.IndentingStringWriter;
import org.hamcrest.Matcher;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestHelper {

    public static void assertSource(SourceGenerator sourceGenerator, Matcher<String> matcher) throws IOException {
        StringWriter writer = new StringWriter();
        sourceGenerator.appendSource(new IndentingStringWriter(writer));
        try {
            assertThat(writer.toString().replaceAll("    ", ""), matcher);
        } catch (AssertionError e) {
            System.out.println(writer.toString());
            throw e;
        }
    }

    public static void assertUsageSource(Statement statement, Matcher<String> matcher) throws IOException {
        StringWriter writer = new StringWriter();
        statement.appendSource(new IndentingStringWriter(writer));
        assertThat(writer.toString().replaceAll("    ", ""), matcher);
    }

    static void assertNoImportsFor(HasImports hasImports) {
        assertImportsFor(hasImports, Collections.EMPTY_SET);
    }

    static void assertImportsFor(HasImports hasImports, Set<MutableClass> imports) {
        assertEquals(hasImports.getImports(), imports);
    }

    public static String readFileAsString(Object owner, String testCase) throws java.io.IOException {
        final String fileName = owner.getClass().getSimpleName() + "_" + testCase + ".txt";
        return IOUtils.toString(owner.getClass().getResourceAsStream(fileName)).replaceAll("    ", "");
    }
}
