package epsilongtmyon.sandbox02.app.common.exec.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

import epsilongtmyon.sandbox02.app.common.exec.condition.BatchCondition;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(BatchCondition.class)
public @interface Batch {

	String value();
}
