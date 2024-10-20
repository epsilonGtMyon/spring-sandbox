package epsilongtmyon.sandbox05.app.common.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

public class SimpleManualTransaction {

	private static final Logger logger = LoggerFactory.getLogger(SimpleManualTransaction.class);

	private final PlatformTransactionManager txManager;
	private final TransactionDefinition txDef;
	private TransactionStatus txStatus;

	public SimpleManualTransaction(PlatformTransactionManager txManager, TransactionDefinition txDef) {
		super();
		this.txManager = txManager;
		this.txDef = txDef;
	}

	protected void begin() {
		if (txStatus != null) {
			throw new IllegalStateException("transaction " + txDef.getName() + " is already begun.");
		}
		logger.info("begin manual transaction: {} {}", new Object[] { txDef.getName(), txDef });
		this.txStatus = txManager.getTransaction(txDef);
	}

	public void commit() {
		if (txStatus.isCompleted()) {
			logger.warn("transaction {0} is already completed.", new Object[] { txDef.getName() });
			return;
		}
		logger.info("commit manual transaction: {}", new Object[] { txDef.getName() });
		txManager.commit(txStatus);
	}

	public void rollback() {
		if (txStatus.isCompleted()) {
			logger.warn("transaction {0} is already completed.", new Object[] { txDef.getName() });
			return;
		}
		logger.info("rollback manual transaction: {}", new Object[] { txDef.getName() });
		txManager.rollback(txStatus);
	}
}
