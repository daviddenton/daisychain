package org.daisychain.core.generate;

import java.io.IOException;
import java.io.StringWriter;

public class LogToStdOutProcessor implements ClasspathWalker.Processor {

    public void process(Class clazz) throws IOException {
        StringWriter writer = new StringWriter();
        StrictDslGenerator.generateDslSourceFor(clazz).appendTo(writer);
        System.out.println(writer.toString());
    }
}