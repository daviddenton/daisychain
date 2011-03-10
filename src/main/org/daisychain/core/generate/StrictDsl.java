package org.daisychain.core.generate;

import org.daisychain.core.annotate.GeneratedDsl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class StrictDsl<BuiltType> {
    private final List<Object> args = new ArrayList<Object>();
    private final Chain<BuiltType> chain;

    public static <BuiltType, EntryPointClass> EntryPointClass create(Class<? extends EntryPoint<BuiltType, EntryPointClass>> entryPointClass) {
        DslSpec<BuiltType> spec = DslSpec.specFor(entryPointClass.getEnclosingClass().getAnnotation(GeneratedDsl.class).target());
        Chain<BuiltType> chain = chainNamed(spec, entryPointClass.getEnclosingClass().getSimpleName());
        return (EntryPointClass) new StrictDsl<BuiltType>(chain).proxyFor(entryPointClass);
    }

    private StrictDsl(Chain<BuiltType> chain) {
        this.chain = chain;
    }

    private static <T> Chain<T> chainNamed(DslSpec<T> spec, String name) {
        for (Chain<T> chain : spec.chains) {
            if(chain.intro.equals(name)) return chain;
        }
        throw new IllegalArgumentException("No Chain named "+name);
    }

    private boolean addNextArgAndCheckIfMore(Object o) {
        validateForOptionalParam(o);
        args.add(o);
        return chain.getParameterSignatures().size() == args.size();
    }

    private void validateForOptionalParam(Object o) {
        if(o==null && !chain.getParameterSignatures().get(args.size()).param.optional()) {
            throw new IllegalArgumentException("Parameter at index " + args.size() + " of [" + chain.methodToString() + "] cannot be null");
        }
    }

    private Builder<BuiltType> builder() {
        return new Builder<BuiltType>() {
            public BuiltType build() {
                try {
                    return chain.invoke(args.toArray());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private <T> T proxyFor(final Class<T> aClass) {
        return aClass.cast(Proxy.newProxyInstance(aClass.getClassLoader(), new Class[]{aClass}, new InvocationHandler() {
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                boolean complete = addNextArgAndCheckIfMore(objects[0]);
                return complete ? builder() : proxyFor(aClass.getMethods()[0].getReturnType());
            }
        }));
    }

}
