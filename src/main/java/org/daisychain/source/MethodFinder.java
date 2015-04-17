package org.daisychain.source;

public class MethodFinder<T extends Method> {
    private final AClass<T> aClass;

    public MethodFinder(AClass<T> aClass) {
        this.aClass = aClass;
    }

    public T method(String methodName, int argumentCount) {
        for (T method : aClass.methods()) {
            if (method.name().equals(methodName) && method.parameters().size() == argumentCount) return method;
        }
        throw new IllegalArgumentException("Can't find matching method " + methodName + " in" + aClass);
    }

    public Constructor constructor(int argumentCount) {
        for (Constructor constructor : aClass.constructors()) {
            if (constructor.parameters().size() == argumentCount) return constructor;
        }
        throw new IllegalArgumentException("Can't find matching constructor in " + aClass.simpleName() + " for parameters " + argumentCount);
    }
}
