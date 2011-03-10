package org.daisychain.source;

import org.daisychain.source.body.AssignableStatement;

import java.util.List;

public interface Method extends HasImports {
    String name();
    List<Annotation> annotations();
    List<Parameter> parameters();
    List<AClass> exceptions();
    AClass returnType();
    AssignableStatement call(List<AssignableStatement> arguments);
}
