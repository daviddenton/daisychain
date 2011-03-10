package org.daisychain.core.annotate;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@java.lang.annotation.Retention(RUNTIME)
@java.lang.annotation.Target({CONSTRUCTOR, METHOD})
public @interface Dsl {
    enum InvocationType { Static, Dynamic }
    enum Ordering { Strict, Loose }
    String intro() default "create";
    Ordering ordering() default Ordering.Strict;
    InvocationType invocationType() default InvocationType.Dynamic;
}
