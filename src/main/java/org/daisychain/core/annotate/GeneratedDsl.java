package org.daisychain.core.annotate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@java.lang.annotation.Retention(RUNTIME)
@java.lang.annotation.Target({TYPE})
public @interface GeneratedDsl {
    public abstract Class target();
}