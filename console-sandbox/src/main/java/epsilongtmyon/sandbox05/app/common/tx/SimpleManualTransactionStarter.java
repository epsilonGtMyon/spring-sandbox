package epsilongtmyon.sandbox05.app.common.tx;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class SimpleManualTransactionStarter {

	private final PlatformTransactionManager txManager;

	public SimpleManualTransactionStarter(PlatformTransactionManager txManager) {
		super();
		this.txManager = txManager;
	}

	public SimpleManualTransaction start() {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		String txName = UUID.randomUUID().toString();
		txDef.setName(txName);
		return start(txDef);
	}

	public SimpleManualTransaction startRequiredNew() {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		String txName = UUID.randomUUID().toString();
		txDef.setName(txName);
		txDef.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
		return start(txDef);
	}

	public SimpleManualTransaction start(TransactionDefinition txDef) {
		SimpleManualTransaction tx = new SimpleManualTransaction(txManager, txDef);
		tx.begin();
		return tx;
	}
}
