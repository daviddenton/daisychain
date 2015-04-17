package org.daisychain.source.body;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.AClass;
import org.daisychain.source.Constructor;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ConstructorCall extends AbstractMethodCall {
    private final AClass target;

    public ConstructorCall(AClass target, Constructor constructor, List<AssignableStatement> arguments) {
        super(constructor, arguments);
        this.target = target;
    }

    public Set<AClass> getImports() {
        Set<AClass> imports = super.getImports();
        imports.addAll(target.getImports());
        return imports;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append("new ");
        super.appendSource(writer);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

}