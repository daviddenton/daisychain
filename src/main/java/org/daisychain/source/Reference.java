package org.daisychain.source;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class Reference implements SourceGenerator, AssignableStatement {

    public final String name;

    public Reference(String name) {
        this.name = name;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append(name);
    }


    public AssignableStatement assignTo(final AssignableStatement statement) {
        return new AssignableStatement() {
            public void appendSource(IndentingStringWriter writer) throws IOException {
                Reference.this.appendSource(writer);
                writer.append(" = ");
                statement.appendSource(writer);
            }

            public Set<AClass> getImports() {
                return ImportExtractor.extractImportsFrom(statement.getImports());
            }
        };
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reference reference = (Reference) o;

        if (name != null ? !name.equals(reference.name) : reference.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Set<AClass> getImports() {
        return Collections.EMPTY_SET;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
