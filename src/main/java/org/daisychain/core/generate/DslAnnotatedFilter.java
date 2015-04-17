package org.daisychain.core.generate;

public class DslAnnotatedFilter implements ClasspathWalker.Filter {
    public boolean accept(Class clazz) {
        try {
            DslSpec.specFor(clazz);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
