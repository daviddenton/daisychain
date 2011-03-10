package org.daisychain.util;

public interface Functor<T, E extends Throwable> {
    void execute(T target) throws E;
}