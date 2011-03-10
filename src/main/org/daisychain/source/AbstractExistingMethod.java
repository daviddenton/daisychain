package org.daisychain.source;

import org.apache.commons.lang.builder.ToStringBuilder;
import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;
import static org.daisychain.source.HasImports.ImportExtractor.extractImportsFrom;
import org.daisychain.source.body.AssignableStatement;
import org.daisychain.source.util.IndentingStringWriter;
import org.daisychain.source.util.ItemContent;
import static org.daisychain.source.util.ListAppender.loopWrapped;

import java.io.IOException;
import java.util.*;
import static java.util.Collections.EMPTY_LIST;

public abstract class AbstractExistingMethod implements Method {
    private final String name;
    private final java.lang.annotation.Annotation[] annotations;
    private final Class<?> returnType;
    private final Class[] arguments;
    private final Class[] exceptions;

    public AbstractExistingMethod(java.lang.reflect.Method method) {
        this(method.getName(), method.getParameterTypes(), method.getExceptionTypes(), method.getAnnotations(), method.getReturnType());
    }

    protected AbstractExistingMethod(String name, Class[] arguments, Class[] exceptions, java.lang.annotation.Annotation[] annotations, Class<?> returnType) {
        this.name = name;
        this.arguments = arguments;
        this.exceptions = exceptions;
        this.annotations = annotations;
        this.returnType = returnType;
    }

    public String name() {
        return name;
    }

    public AClass returnType() {
        return ExistingClass.existingClass(returnType);
    }

    public AssignableStatement call(final List<AssignableStatement> arguments) {
        return new AssignableStatement() {
            public Set<AClass> getImports() {
                return extractImportsFrom(arguments);
            }

            public void appendSource(IndentingStringWriter writer) throws IOException {
                loopWrapped(arguments).withPrefix(name + "(").commaSeperated().andForEach(new ItemContent<AssignableStatement>() {
                    public void append(IndentingStringWriter writer, AssignableStatement target) throws IOException {
                        target.appendSource(writer);
                    }
                }).withSuffix(")").to(writer);
            }
        };
    }

    public List<Parameter> parameters() {
        List<Parameter> parameters = new ArrayList<Parameter>();
        for (Class aClass : arguments) {
            ExistingClass type = ExistingClass.existingClass(aClass, EMPTY_LIST);
            parameters.add(new Parameter(Parameter.ParameterModifier.Final, new Instance(type.simpleName(), type)));
        }
        return parameters;
    }

    public List<Annotation> annotations() {
        List<Annotation> annotations = new ArrayList<Annotation>();
        for (java.lang.annotation.Annotation annotation : this.annotations) {
            annotations.add(new Annotation(ExistingClass.existingClass(annotation.annotationType()), new HashMap()));
        }
        return annotations;
    }

    public List<AClass> exceptions() {
        List<AClass> exceptions = new ArrayList<AClass>();
        for (Class aClass : this.exceptions) {
            exceptions.add(ExistingClass.existingClass(aClass, EMPTY_LIST));
        }
        return exceptions;
    }

    public Set<AClass> getImports() {
        return extractImportsFrom(parameters());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    public ClassMethod override() {
        ClassMethod method = new ClassMethod(annotations(), Collections.singletonList(Modifier.Public), ExistingClass.existingClass(returnType), name);
        for (AClass exception : exceptions()) {
            method.addException(exception);
        }
        for (Parameter parameter : parameters()) {
            method.addParameter(parameter);
        }
        return method;
    }
}
