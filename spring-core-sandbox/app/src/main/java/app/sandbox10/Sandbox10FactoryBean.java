package app.sandbox10;

import org.springframework.beans.factory.FactoryBean;

public class Sandbox10FactoryBean implements FactoryBean<Sandbox10Hoge> {

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public Class<?> getObjectType() {
		return Sandbox10Hoge.class;
	}

	@Override
	public Sandbox10Hoge getObject() throws Exception {
		System.out.println("よばれた");
		return new Sandbox10Hoge();
	}

}
