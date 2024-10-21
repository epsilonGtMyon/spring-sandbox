package epsilongtmyon.sandbox05.app.common.tx;

import java.util.UUID;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public interface SimpleManualTransactionStarter<T extends SimpleManualTransaction> {

	default T start() {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		String txName = UUID.randomUUID().toString();
		txDef.setName(txName);
		return start(txDef);
	}

	default T startRequiredNew() {
		DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();
		String txName = UUID.randomUUID().toString();
		txDef.setName(txName);
		txDef.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
		return start(txDef);
	}

	T start(TransactionDefinition txDef);
}
