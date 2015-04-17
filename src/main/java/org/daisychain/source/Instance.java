package org.daisychain.source;

import com.google.common.base.Function;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.body.MethodCall;
import org.daisychain.source.util.IndentingStringWriter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Instance implements HasImports, SourceGenerator {
    public final AClass aClass;
    public final Reference reference;

    public Instance(String name, AClass aClass) {
        this.aClass = aClass;
        this.reference = new Reference(name);
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        aClass.appendSource(writer);
        writer.space();
        reference.appendSource(writer);
    }

    public Set<AClass> getImports() {
        return aClass.getImports();
    }

    public AssignableStatement call(final String methodName, List<AssignableStatement> arguments) {
        return new MethodCall(reference, aClass.method(methodName, arguments.size()), arguments);
    }

    public AssignableStatement call(String methodName) {
        final Arguments arguments = new Arguments();
        return call(methodName, arguments.asStatements());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instance that = (Instance) o;

        if (aClass != null ? !aClass.equals(that.aClass) : that.aClass != null) return false;
        if (reference != null ? !reference.equals(that.reference) : that.reference != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aClass != null ? aClass.hashCode() : 0;
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    public static List<AClass> transformToAClass(List<Instance> arguments) {
        return newArrayList(transform(arguments, new Function<Instance, AClass>() {
            public AClass apply(Instance instance) {
                return instance.aClass;
            }
        }));
    }
}
