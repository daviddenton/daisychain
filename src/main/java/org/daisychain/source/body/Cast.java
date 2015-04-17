package org.daisychain.source.body;

import org.daisychain.source.AClass;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.Set;

public class Cast implements AssignableStatement {

    private final AClass castToType;
    private final AssignableStatement targetStatement;

    public Cast(AClass castToType, AssignableStatement targetStatement) {
        this.castToType = castToType;
        this.targetStatement = targetStatement;
    }

    public Set<AClass> getImports() {
        Set<AClass> imports = targetStatement.getImports();
        imports.addAll(castToType.getImports());
        return imports;
    }

    public void appendSource(IndentingStringWriter indentingStringWriter) throws IOException {
        indentingStringWriter.append("(").append(castToType.simpleName()).append(") ");
        targetStatement.appendSource(indentingStringWriter);
    }
}
