package org.osforce.connect.web.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author <a href="mailto:haozhonghu@hotmail.com">gavin</a>
 * @since 1.1.0
 * @create May 20, 2011 - 3:49:27 AM
 * <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {

	String[] value() default {};
	
	boolean userRequired() default false;
	
	boolean projectRequired() default false;
}
