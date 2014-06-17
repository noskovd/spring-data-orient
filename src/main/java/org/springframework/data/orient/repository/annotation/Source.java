package org.springframework.data.orient.repository.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.orient.repository.SourceType;

/**
 * The annotation to declare orient source (class or cluster).
 * 
 * @author Dzmitry_Naskou
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Source {

    /**
     * Defines the Orient source type. Defaults to {@link SourceType#CLUSTER}.
     *
     * @return the source type
     */
    SourceType type() default SourceType.CLUSTER;
    
    /**
     * Defines the Orient cluster name.
     */
    String value();
}
