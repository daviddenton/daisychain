package org.daisychain.source.body;

import org.daisychain.source.util.IndentingStringWriter;
import org.daisychain.source.util.ItemContent;
import static org.daisychain.source.util.ListAppender.loopWrapped;
import org.daisychain.source.AClass;
import org.daisychain.source.Method;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public abstract class AbstractMethodCall implements AssignableStatement {
    private final Method method;
    protected final List<AssignableStatement> arguments;

    public AbstractMethodCall(Method method, List<AssignableStatement> arguments) {
        this.method = method;
        this.arguments = arguments;
    }
    
    public Set<AClass> getImports() {
        return extractImportsFrom(arguments);
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        method.call(arguments).appendSource(writer);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
