package org.daisychain.core.generate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ConstructorChain<T> extends Chain<T> {
    private final Constructor constructor;

    public ConstructorChain(Constructor constructor, String intro) {
        super(intro);
        this.constructor = constructor;
    }

    public List<ParameterSignature> getParameterSignatures() {
        List<ParameterSignature> parameterSignatures = new ArrayList<ParameterSignature>();
        for (int i = 0; i < constructor.getParameterTypes().length; i++) {
            parameterSignatures.add(new ParameterSignature(i, constructor.getParameterTypes()[i],
                    constructor.getGenericParameterTypes()[i],
                    paramAnnotationAt(constructor.getParameterAnnotations()[i])));

        }
        return parameterSignatures;
    }

    public T invoke(Object[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) constructor.newInstance(args);
    }

    public String methodToString() {
        return constructor.toGenericString();
    }

}
