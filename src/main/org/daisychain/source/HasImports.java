package org.daisychain.source;

import java.util.HashSet;
import java.util.Set;

public interface HasImports {
    Set<AClass> getImports();

    public static class ImportExtractor {
        private ImportExtractor() {}
        public static Set<AClass> extractImportsFrom(Iterable<? extends HasImports>... from) {
            Set<AClass> imports = new HashSet<AClass>();
            for (Iterable<? extends HasImports> iterable : from) {
                for (HasImports hasImports : iterable) {
                    imports.addAll(hasImports.getImports());
                }
            }
            return imports;
        }
    }
}
