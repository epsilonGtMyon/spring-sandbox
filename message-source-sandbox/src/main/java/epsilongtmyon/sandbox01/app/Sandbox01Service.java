package epsilongtmyon.sandbox01.app;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;

@Component
public class Sandbox01Service {

	private final MessageSource messageSource;

	public Sandbox01Service(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	public void doService() {

		// メッセージのプレースホルダーはObject[]で渡す
		// 最後のロケールをnullにした場合はデフォルトのものが使われる
		// AbstractMessageSource#getMessageInternal
		String message01 = messageSource.getMessage("message01", new Object[] { "アイテム０１" }, null);
		System.out.println(message01);

		// argsもリソースで解決したい場合は MessageSourceResolvableを渡す
		// MessageSourceResolvableはString[]をcodesとして持つがこれは最初に見つかったものを返している(AbstractMessageSource#getMessageを参照)
		String message02a = messageSource.getMessage("message02",
				new Object[] { new DefaultMessageSourceResolvable("item02") }, null);
		System.out.println(message02a);

		// MessageSourceResolvableの先頭のcodeが存在しない場合
		String message02b = messageSource.getMessage("message02",
				new Object[] { new DefaultMessageSourceResolvable(new String[] { "itemxx", "item03" }) }, null);
		System.out.println(message02b);

		// MessageSourceResolvableのcodeが存在しない場合、デフォルトが使われる
		String message02c = messageSource.getMessage("message02",
				new Object[] { new DefaultMessageSourceResolvable(new String[] { "itemxx" }, "アイテムなし") }, null);
		System.out.println(message02c);

		// メッセージ引数省略時
		String message03 = messageSource.getMessage("message03", null, null);
		System.out.println(message03);

		// 存在しないメッセージコード指定時
		// デフォルトだと例外おきるが AbstractMessageSource#setUseCodeAsDefaultMessageをtrueにするとコードを返す
		String message04 = messageSource.getMessage("message04", null, null);
		System.out.println(message04);
	}

}
