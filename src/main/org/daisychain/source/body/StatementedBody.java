package org.daisychain.source.body;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.AClass;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.util.IndentingStringWriter;
import static org.daisychain.source.util.ListAppender.generateSource;
import static org.daisychain.source.util.ListAppender.loop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StatementedBody implements Body {

    private final List<Statement> statements = new ArrayList<Statement>();

    public StatementedBody addStatement(Statement statement) {
        statements.add(statement);
        return this;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.indent().append(" {");
        loop(statements).withPrefix("\n").andForEach(generateSource()).seperatedBy(";\n").withSuffix(";").to(writer);
        writer.exdent().newLine().append("}");
    }

    public Set<AClass> getImports() {
        return extractImportsFrom(statements);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
    
}

