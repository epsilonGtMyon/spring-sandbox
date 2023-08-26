package epsilongtmyon.lib.autoconfigure.condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;

/**
 * {@link Environment} の特定キーに含まれているかどうかで有効化を行うConditionalアノテーション
 * 
 * Environment側の値はカンマ区切りで複数指定されていてもよい
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnEnvContainsCondition2.class)
public @interface ConditionalOnEnvContains2 {

	/**
	 * 有効化判定に使用するキー
	 * 
	 * @return 有効化判定に使用するキー
	 */
	String envKey() default "env.contains2";

}
