package org.daisychain.source.body;

import org.daisychain.source.AClass;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.util.IndentingStringWriter;
import static org.daisychain.source.util.ListAppender.loop;
import static org.daisychain.source.util.ListAppender.generateSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Statements implements Statement {

    private final List<Statement> statements = new ArrayList<Statement>();

    public Statements addStatement(Statement statement) {
        statements.add(statement);
        return this;
    }

    public Set<AClass> getImports() {
        return extractImportsFrom(statements);
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        loop(statements).seperatedBy(";\n").andForEach(generateSource()).to(writer);
    }
}
