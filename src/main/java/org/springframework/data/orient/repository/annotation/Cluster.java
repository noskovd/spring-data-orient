package org.springframework.data.orient.repository.annotation;

import java.lang.annotation.Documented;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation to declare orient cluster.
 * 
 * @author Dzmitry_Naskou
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cluster {

    /**
     * Defines the Orient cluster.
     */
    String value() default "";
}
