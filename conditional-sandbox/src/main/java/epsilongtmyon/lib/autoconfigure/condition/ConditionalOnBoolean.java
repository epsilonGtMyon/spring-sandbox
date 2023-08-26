package epsilongtmyon.lib.autoconfigure.condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

/**
 * true/falseの値だけで有効化を行うConditionalアノテーション
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnBooleanCondition.class)
public @interface ConditionalOnBoolean {

	/**
	 * 値
	 * 
	 * @return 値
	 */
	boolean value() default false;
}
