package app.sandbox26;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//EventListenerMethodProcessorで検知・登録される
@Component
public class Sandbox26EventListener {

	//メソッドはApplicationListenerMethodAdapterにラップされる
	//アノテーションの場合は戻り値を戻すことができて
	//その場合は連鎖することができる。
	//その機能はApplicationListenerMethodAdapterで閉じてるっぽい
	//Collectionを返せば複数のイベントをpublishできる。
	@EventListener
	public Sandbox26Event2 onEvent(Sandbox26Event event) {
		System.out.println(getClass());
		System.out.println(event);
		System.out.println(event.getSource());

		return new Sandbox26Event2("event!");
	}

	@EventListener
	public void onEvent(Sandbox26Event2 event) {
		System.out.println(getClass());
		System.out.println(event);
		System.out.println(event.getSource());
	}
}
