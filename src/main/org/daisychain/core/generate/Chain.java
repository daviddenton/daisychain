package org.daisychain.core.generate;

import org.daisychain.core.annotate.Param;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import static java.util.Arrays.asList;
import java.util.List;

public abstract class Chain<T> {
    public final String intro;

    public Chain(String intro) {
        this.intro = intro;
    }

    public abstract List<ParameterSignature> getParameterSignatures();

    public abstract T invoke(Object[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException;

    public abstract String methodToString();

    Param paramAnnotationAt(Annotation[] parameterAnnotation) {
        for (Annotation annotation : asList(parameterAnnotation)) {
            if(annotation.annotationType() == Param.class) return Param.class.cast(annotation);
        }

        throw new IllegalArgumentException("Not all parameters in method " + methodToString() + " are annotated");
    }

}
