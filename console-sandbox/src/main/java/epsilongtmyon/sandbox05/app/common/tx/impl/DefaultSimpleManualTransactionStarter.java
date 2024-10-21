package epsilongtmyon.sandbox05.app.common.tx.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import epsilongtmyon.sandbox05.app.common.tx.SimpleManualTransactionStarter;

@Component
public class DefaultSimpleManualTransactionStarter implements SimpleManualTransactionStarter<DefaultSimpleManualTransaction> {

	private final PlatformTransactionManager txManager;

	public DefaultSimpleManualTransactionStarter(PlatformTransactionManager txManager) {
		super();
		this.txManager = txManager;
	}

	@Override
	public DefaultSimpleManualTransaction start(TransactionDefinition txDef) {
		DefaultSimpleManualTransaction tx = new DefaultSimpleManualTransaction(txManager, txDef);
		tx.begin();
		return tx;
	}

}
