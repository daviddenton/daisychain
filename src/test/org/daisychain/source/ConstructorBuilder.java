package org.daisychain.source;

import org.daisychain.source.body.AssignableStatement;

import java.util.List;
import java.util.Set;

public class ConstructorBuilder extends MethodBuilder {

    private ConstructorBuilder() {
    }

    public static ConstructorBuilder aConstructor() {
        return new ConstructorBuilder();
    }

    public Constructor build() {
        return new Constructor() {

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