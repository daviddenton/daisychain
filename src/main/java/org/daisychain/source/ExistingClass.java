package org.daisychain.source;

import com.google.common.base.Function;
import static com.google.common.collect.Iterables.transform;
import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.body.ConstructorCall;
import org.daisychain.source.body.StaticMethodCall;
import org.daisychain.source.util.IndentingStringWriter;
import static org.daisychain.source.util.ListAppender.generateSource;
import static org.daisychain.source.util.ListAppender.loop;

import java.io.IOException;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import java.util.List;
import java.util.Set;

public class ExistingClass implements AClass<ExistingMethod> {

    private final Class aClass;
    private final List<? extends AClass> generics;
    private final MethodFinder<ExistingMethod> methodFinder;

    public static final AClass VOID = new ExistingClass(Void.class) {
        @Override
        public void appendSource(IndentingStringWriter writer) throws IOException {
            writer.append("void");
        }
    };

    private ExistingClass(Class aClass) {
        this(aClass, EMPTY_LIST);
    }

    private ExistingClass(Class aClass, List<? extends AClass> generics) {
        this.aClass = aClass;
        this.generics = generics;
        this.methodFinder = new MethodFinder(this);
    }

    public static ExistingClass existingClass(Class aClass) {
        return new ExistingClass(aClass);
    }

    public static ExistingClass existingClass(Class aClass, List<? extends AClass> generics) {
        return new ExistingClass(aClass, generics);
    }

    public Set<AClass> getImports() {
        final Set<AClass> imports = extractImportsFrom(generics);
        imports.add(this);
        return imports;
    }

    public int compareTo(AClass that) {
        return name().compareTo(that.name());
    }

    public String name() {
        return aClass.getName();
    }

    public String simpleName() {
        return aClass.getSimpleName();
    }

    public boolean implementsInterface(Class that) {
        return that.isAssignableFrom(aClass);
    }

    public void appendSource(IndentingStringWriter writer) throws IOException {
        writer.append(aClass.getSimpleName());
        loop(generics).withPrefix("<").andForEach(generateSource()).commaSeperated().withSuffix(">").to(writer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExistingClass that = (ExistingClass) o;

        if (aClass != null ? !aClass.equals(that.aClass) : that.aClass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return aClass != null ? aClass.hashCode() : 0;
    }

    public AssignableStatement instantiate(List<AssignableStatement> arguments) {
        return new ConstructorCall(this, methodFinder.constructor(arguments.size()), arguments);
    }

    public boolean isPrimitive() {
        return aClass.isPrimitive();
    }

    public Iterable<ExistingMethod> methods() {
        return transform(asList(aClass.getMethods()), new Function<java.lang.reflect.Method, ExistingMethod>() {
            public ExistingMethod apply(java.lang.reflect.Method o) {
                return new ExistingMethod(o);
            }
        });
    }

    public ExistingMethod method(String method, int argumentCount) {
        return methodFinder.method(method, argumentCount);
    }

    public AssignableStatement call(final String methodName, List<AssignableStatement> arguments) {
        return new StaticMethodCall(this, methodFinder.method(methodName, arguments.size()), arguments);
    }

    public AssignableStatement call(String methodName) {
        return call(methodName, new Arguments().asStatements());
    }

    public Iterable<Constructor> constructors() {
        return transform(asList(aClass.getConstructors()), new Function<java.lang.reflect.Constructor, Constructor>() {
            public Constructor apply(java.lang.reflect.Constructor o) {
                return new ExistingConstructor(o);
            }
        });
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

}