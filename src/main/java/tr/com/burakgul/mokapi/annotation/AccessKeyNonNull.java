package tr.com.burakgul.mokapi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * If access key parameter is null, does not run method.
 * Throw "ResponseStatusException" with http status unauthorized in runtime.
 *
 * @author Burak GUL
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface AccessKeyNonNull {
}
