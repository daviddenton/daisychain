package org.daisychain.source.body;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.AClass;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.Instance;
import org.daisychain.source.util.IndentingStringWriter;
import static org.daisychain.source.util.ListAppender.generateSource;
import static org.daisychain.source.util.ListAppender.loopWrapped;

import java.util.List;
import java.util.Set;

public class Instantiation implements AssignableStatement {
    private final AClass aClass;
    private final List<Instance> arguments;

    public Instantiation(AClass aClass, List<Instance> arguments) {
        this.aClass = aClass;
        this.arguments = arguments;
    }

    public Set<AClass> getImports() {
        return extractImportsFrom(arguments);
    }

    public void appendSource(IndentingStringWriter writer) {
        loopWrapped(arguments).withPrefix(" new "+aClass.name()+"(").andForEach(generateSource()).withSuffix(")");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
    
}
