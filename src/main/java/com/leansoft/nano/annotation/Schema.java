package com.leansoft.nano.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation, if presents on a POJO, 
 * indicates namespace for all contained classes
 * 
 * @author bulldog
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Schema {

    /**
     * The namespace of the whole package
     * 
     * @return namespace
     */
    public String namespace() default "";
	
}
