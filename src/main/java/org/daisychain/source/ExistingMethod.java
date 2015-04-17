package org.daisychain.source;

import java.lang.reflect.Method;

public class ExistingMethod extends AbstractExistingMethod {

    public ExistingMethod(Method method) {
        super(method.getName(), method.getParameterTypes(), method.getExceptionTypes(), method.getAnnotations(), method.getReturnType());
    }
}