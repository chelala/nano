package com.leansoft.nano.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation, if presents on a POJO, indicates Type to be used in XML Element
 * 
 * @author bulldog
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Type {

    /**
     * The name of the Type
     * 
     * @return name
     */
    public String name() default "";

}
