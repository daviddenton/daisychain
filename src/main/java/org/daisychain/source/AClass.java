package org.daisychain.source;

import org.daisychain.source.body.AssignableStatement;

import java.util.List;

public interface AClass<T extends Method> extends HasImports, SourceGenerator, Comparable<AClass> {
    String name();
    String simpleName();
    boolean implementsInterface(Class that);
    Iterable<T> methods();
    T method(String method, int argumentCount);
    AssignableStatement call(final String methodName, List<AssignableStatement> arguments);
    AssignableStatement call(final String methodName);
    Iterable<Constructor> constructors();
    AssignableStatement instantiate(List<AssignableStatement> arguments);
    boolean isPrimitive();
}
