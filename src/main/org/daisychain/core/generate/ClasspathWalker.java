package org.daisychain.core.generate;

import java.io.IOException;

public interface ClasspathWalker {
    void forAllClasses(Filter filter, Processor processor) throws IOException;

    interface Filter {
        boolean accept(Class clazz);
    }

    interface Processor {
        void process(Class clazz) throws IOException;
    }
}
