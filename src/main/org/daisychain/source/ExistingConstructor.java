package org.daisychain.source;

public class ExistingConstructor extends AbstractExistingMethod implements Constructor {

    public ExistingConstructor(java.lang.reflect.Constructor constructor) {
        super(constructor.getDeclaringClass().getSimpleName(), constructor.getParameterTypes(), constructor.getExceptionTypes(), constructor.getAnnotations(), constructor.getDeclaringClass());
    }
}