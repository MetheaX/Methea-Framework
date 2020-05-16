package io.methea.repository.hibernateextension.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectFrom {
    String fromClause() default "";
    String join() default "";
    String getById() default "o.id = :id";
    String orderBy() default "";
}
