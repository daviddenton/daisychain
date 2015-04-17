package org.daisychain.source;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.daisychain.source.AssignableStatementBuilder.anAssignableStatement;
import org.daisychain.source.body.AssignableStatement;

import java.util.List;
import java.util.Set;

public class MethodBuilder<T extends MethodBuilder> {
    Set<AClass> imports = newHashSet();
    List<Parameter> parameters = newArrayList();
    List<AClass> exceptions = newArrayList();
    List<Annotation> annotations = newArrayList();
    String name = "aMethodName";
    AssignableStatement statement = anAssignableStatement().build();
    protected AClass returnType;

    MethodBuilder() {
    }

    public static MethodBuilder aMethod() {
        return new MethodBuilder();
    }

    public T withImport(AClass anImport) {
        this.imports.add(anImport);
        return (T) this;
    }

    public T withAnnotation(Annotation annotation) {
        this.annotations.add(annotation);
        return (T) this;
    }

    public T withParameter(Parameter parameter) {
        this.parameters.add(parameter);
        return (T) this;
    }

    public T whichReturns(AClass returnType) {
        this.returnType = returnType;
        return (T) this;
    }

    public T withException(AClass exception) {
        this.exceptions.add(exception);
        return (T) this;
    }

    public T withName(String name) {
        this.name = name;
        return (T) this;
    }

    public T whichProducesStatement(AssignableStatement statement) {
        this.statement = statement;
        return (T) this;
    }

    public Method build() {
        return new Method() {

            public String name() {
                return name;
            }

            public List<Annotation> annotations() {
                return annotations;
            }

            public List<Parameter> parameters() {
                return parameters;
            }

            public List<AClass> exceptions() {
                return exceptions;
            }

            public AClass returnType() {
                return returnType;
            }

            public AssignableStatement call(List<AssignableStatement> arguments) {
                return statement;
            }

            public AssignableStatement call() {
                return statement;
            }

            public Set<AClass> getImports() {
                return imports;
            }
        };
    }
}