package org.daisychain.core.generate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StaticMethodChain<T> extends Chain<T> {
    private final Method method;

    public StaticMethodChain(Method method, String intro) {
        super(intro);
        this.method = method;
    }

    public List<ParameterSignature> getParameterSignatures() {
        List<ParameterSignature> parameterSignatures = new ArrayList<ParameterSignature>();
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            parameterSignatures.add(new ParameterSignature(i, method.getParameterTypes()[i],
                    method.getGenericParameterTypes()[i],
                    paramAnnotationAt(method.getParameterAnnotations()[i])));

        }
        return parameterSignatures;
    }

    public T invoke(Object[] args) throws IllegalAccessException, InvocationTargetException {
        return (T) method.invoke(null, args);
    }

    public String methodToString() {
        return method.toGenericString();
    }

}
