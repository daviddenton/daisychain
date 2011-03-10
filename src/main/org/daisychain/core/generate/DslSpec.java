package org.daisychain.core.generate;

import org.daisychain.core.annotate.Dsl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

public class DslSpec<T> {
    public final Class<? extends T> aClass;
    public final Collection<Chain<T>> chains = new ArrayList<Chain<T>>();

    public static <T> DslSpec<T> specFor(Class<? extends T> aClass) {
        return new DslSpec<T>(aClass);
    }

    private DslSpec(Class<? extends T> aClass) {
        this.aClass = aClass;
        addConstructorDslsTo(chains);
        addMethodDslsTo(chains);
        if (chains.isEmpty())
            throw new IllegalArgumentException("Class " + aClass.getName() + " is not annotated");
    }

    private void addMethodDslsTo(Collection<Chain<T>> chains) {
        for (Method method : aClass.getMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.isAnnotationPresent(Dsl.class)) {
                chains.add(new StaticMethodChain<T>(method, method.getAnnotation(Dsl.class).intro()));
            }
        }
    }

    private void addConstructorDslsTo(Collection<Chain<T>> chains) {
        for (Constructor constructor : aClass.getConstructors()) {
            if (constructor.isAnnotationPresent(Dsl.class)) {
                chains.add(new ConstructorChain<T>(constructor, ((Dsl) constructor.getAnnotation(Dsl.class)).intro()));
            }
        }
    }


}
