package app.sandbox27;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class Sandbox27Event<T> extends ApplicationEvent implements ResolvableTypeProvider {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Sandbox27Event(T source) {
		super(source);
	}

	@Override
	public T getSource() {
		return (T) super.getSource();
	}

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getSource()));
    }

	public static class S27EventSource1 {

	}

	public static class S27EventSource2 {

	}
}
