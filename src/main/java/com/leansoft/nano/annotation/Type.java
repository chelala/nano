package com.leansoft.nano.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation, if presents on a POJO, indicates Type to be used in XML Element
 * 
 * @author bulldog
 * 
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Type {

    /**
     * The name of the Type
     * 
     * @return name
     */
    public String name() default "";

}
