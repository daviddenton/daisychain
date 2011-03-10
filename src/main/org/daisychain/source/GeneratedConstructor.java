package org.daisychain.source;

import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.body.Statement;
import org.daisychain.source.body.StatementedBody;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class GeneratedConstructor extends MutableMethod<GeneratedConstructor, StatementedBody> implements Constructor {
    public GeneratedConstructor(List<Annotation> annotations, List<Modifier> modifiers, MutableClass returnType) {
        super(new StatementedBody(), annotations, modifiers, returnType, returnType.simpleName());
    }

    public GeneratedConstructor addStatement(Statement statement) {
        body.addStatement(statement);
        return this;
    }

    @Override
    void appendReturnType(IndentingStringWriter writer) {
    }
}
