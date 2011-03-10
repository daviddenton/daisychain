package org.daisychain.core.annotate;

@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.PARAMETER})
public @interface Param {
    String term() default "with";
    boolean optional() default false;
}
