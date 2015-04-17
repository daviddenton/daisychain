package org.daisychain.source;

import static com.google.common.collect.Sets.newHashSet;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.Set;

public class AssignableStatementBuilder {
    private Set<AClass> imports = newHashSet();
    private String content = "statementContent";

    private AssignableStatementBuilder() {
    }

    public static AssignableStatementBuilder anAssignableStatement() {
        return new AssignableStatementBuilder();
    }

    public AssignableStatementBuilder withImport(AClass aClass) {
        this.imports.add(aClass);
        return this;
    }

    public AssignableStatementBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public AssignableStatement build() {
        return new AssignableStatement() {

            public Set<AClass> getImports() {
                return imports;
            }

            public void appendSource(IndentingStringWriter writer) throws IOException {
                writer.append(content);
            }
        };
    }
}