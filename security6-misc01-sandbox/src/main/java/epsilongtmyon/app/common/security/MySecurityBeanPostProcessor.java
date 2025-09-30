package epsilongtmyon.app.common.security;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.csrf.CsrfFilter;

import epsilongtmyon.app.common.security.MySecurityConfigurerAdapter.MyFilter;

/*
 * Spring SecurityのFilter群などはBean登録されていないのでAutowiredで取得できない。
 * だが、ObjectPostProcessorConfigurationで登録されているAutowireBeanFactoryObjectPostProcessorにより
 * BeanPostProcessorでハンドリングすることはできる。
 * 
 * これはSpring SecurityのObjectPostProcessorにこれが設定されているためである。
 * 
 * AutowireBeanFactoryObjectPostProcessor#postProcessを見た感じ
 * Autowiredつけてたらインジェクションもできる。
 * 
 */
public class MySecurityBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		// フィルターなどのsetterを呼び出すことができる
		// 一見どこから設定するかわからないけど、ここだと介入することができる。
		
		if (bean instanceof CsrfFilter cFilter) {
			Thread.dumpStack();

			// cFilter.setAccessDeniedHandler(null);
		} else if (bean instanceof MyFilter myFilter) {
			System.out.println(myFilter);
		}

		return bean;
	}

}
