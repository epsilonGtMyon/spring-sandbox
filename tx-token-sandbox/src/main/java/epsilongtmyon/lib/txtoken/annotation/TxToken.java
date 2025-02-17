package epsilongtmyon.lib.txtoken.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TxToken {

	/**
	 * どの種類化
	 * @return
	 */
	TxTokenAction value() default TxTokenAction.CHECK;

	/**
	 * アクションの種類
	 */
	public static enum TxTokenAction {
		
		/** 何もしない */
		IGNORE,

		/** チェックをせずトークン発行 */
		PUBLISH,

		/** チェック */
		CHECK,

		;

		public boolean shouldValidate() {
			return this == CHECK;
		}

		public boolean shouldPublish() {
			return this == PUBLISH || this == CHECK;
		}
	}

}
