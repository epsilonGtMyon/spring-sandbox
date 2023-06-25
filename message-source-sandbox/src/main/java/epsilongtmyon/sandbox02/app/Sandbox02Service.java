package epsilongtmyon.sandbox02.app;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;

@Component
public class Sandbox02Service {

	private final MessageSource messageSource;

	public Sandbox02Service(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	public void doService() {

		// メッセージの探索は子→親の順番で行われる
		// AbstractMessageSource#getMessageInternalを見るとその様子がわかる

		// 子にメッセージが存在するパターン
		String message01 = messageSource.getMessage("message01",
				new Object[] { new DefaultMessageSourceResolvable("item01") }, null);
		System.out.println(message01);

		// 子にメッセージが存在せず親に存在するパターン
		String message02 = messageSource.getMessage("message02",
				new Object[] { new DefaultMessageSourceResolvable("item02") }, null);
		System.out.println(message02);
	}

}
