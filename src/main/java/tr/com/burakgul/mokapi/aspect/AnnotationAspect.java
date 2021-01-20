package tr.com.burakgul.mokapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.mokapi.annotation.AccessKeyNonNull;
import tr.com.burakgul.mokapi.annotation.DataExpireDateUpdate;
import tr.com.burakgul.mokapi.model.User;
import tr.com.burakgul.mokapi.repository.UserRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class AnnotationAspect {

    private final UserRepository userRepository;

    public AnnotationAspect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before("execution(* tr.com.burakgul.mokapi.controller..*(..))")
    public void accessKeyNonNullAnnotationAspect(final JoinPoint joinPoint) {
        if (joinPoint.getSignature() instanceof MethodSignature) {
            Parameter[] params = this.getMethodParameters(joinPoint);
            Object[] paramValues = joinPoint.getArgs();
            for (int i = 0; i < params.length; i++) {
                Annotation accessKeyAnnotation = params[i].getAnnotation(AccessKeyNonNull.class);
                Object paramValue = paramValues[i];
                if (accessKeyAnnotation != null && paramValue == null) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This service cannot be accessed without the Access-Key.");
                }
            }
        }
    }

    /**
     * This method updates the expiry date of all records contains accessKey.
     *
     */

    @After("@annotation(dataExpireDateUpdate)")
    public void dataExpireDateUpdateAnnotationAspect(JoinPoint joinPoint, DataExpireDateUpdate dataExpireDateUpdate){
        if (joinPoint.getSignature() instanceof MethodSignature) {
            String accessKeyParamName = dataExpireDateUpdate.accessKeyParamName();
            Parameter[] params = this.getMethodParameters(joinPoint);
            Object[] paramValues = joinPoint.getArgs();
            for (int i = 0; i < params.length; i++) {
                String paramName = params[i].getName();
                Object paramValue = paramValues[i];
                if (accessKeyParamName.equals(paramName) && paramValue != null) {
                    this.expireDateUpdate((String) paramValue);
                    break;
                }
            }
        }
    }

    @Transactional
    public void expireDateUpdate(String accessKey){
        long expireMillis = 432000000L;
        Date date = new Date(System.currentTimeMillis() + expireMillis);
        List<User> userList = userRepository.findByDataKeyContains(accessKey);
        userList.forEach(user -> user.setExpireDate(date));
        userRepository.saveAll(userList);
    }

    private Parameter[] getMethodParameters(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getParameters();
    }
}
