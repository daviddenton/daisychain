package org.daisychain.source.body;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.AClass;
import org.daisychain.source.Method;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class StaticMethodCall extends AbstractMethodCall {
    private final AClass target;

    public StaticMethodCall(AClass target, Method method, List<AssignableStatement> arguments) {
        super(method, arguments);
        this.target = target;
    }

    public Set<AClass> getImports() {
        Set<AClass> imports = super.getImports();
        imports.addAll(target.getImports());
        return imports;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append(target.simpleName()).append(".");
        super.appendSource(writer);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
    
}
