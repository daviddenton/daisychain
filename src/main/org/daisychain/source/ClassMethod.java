package org.daisychain.source;

import org.daisychain.source.body.Statement;
import org.daisychain.source.body.StatementedBody;

import java.util.List;

public class ClassMethod extends MutableMethod<ClassMethod, StatementedBody> {
    public ClassMethod(List<Annotation> annotations, List<Modifier> modifiers, AClass returnType, String name) {
        super(new StatementedBody(), annotations, modifiers, returnType, name);
    }

    public ClassMethod addStatement(Statement statement) {
        body.addStatement(statement);
        return this;
    }
}
