package tr.com.burakgul.mokapi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Updates the expire date of the data received with the access key parameter.
 *
 * @author Burak GUL
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataExpireDateUpdate {
    public String accessKeyParamName() default "accessKey";
}
