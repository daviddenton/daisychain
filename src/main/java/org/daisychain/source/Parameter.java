package org.daisychain.source;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.Set;

public class Parameter implements HasImports, SourceGenerator {

    public static enum ParameterModifier {
        Final("final "), NonFinal("");

        private final String source;

        ParameterModifier(String source) {
            this.source = source;
        }
    }
    
    public final ParameterModifier parameterModifier;
    public final Instance instance;

    public Parameter(ParameterModifier parameterModifier, Instance instance) {
        this.parameterModifier = parameterModifier;
        this.instance = instance;
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append(parameterModifier.source);
        instance.appendSource(writer);
    }

    public Set<AClass> getImports() {
        return instance.getImports();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameter parameter = (Parameter) o;

        if (instance != null ? !instance.equals(parameter.instance) : parameter.instance != null) return false;
        if (parameterModifier != parameter.parameterModifier) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parameterModifier != null ? parameterModifier.hashCode() : 0;
        result = 31 * result + (instance != null ? instance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}