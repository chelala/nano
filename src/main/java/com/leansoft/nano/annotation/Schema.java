package com.leansoft.nano.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation, if presents on a POJO, 
 * indicates namespace for all contained classes
 * 
 * @author bulldog
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface Schema {

    /**
     * The namespace of the whole package
     * 
     * @return namespace
     */
    public String namespace() default "";
	
}
