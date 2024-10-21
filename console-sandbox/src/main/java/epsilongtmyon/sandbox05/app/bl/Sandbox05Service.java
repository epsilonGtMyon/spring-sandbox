package epsilongtmyon.sandbox05.app.bl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import epsilongtmyon.common.db.TableDest;
import epsilongtmyon.common.db.TableDestDao;
import epsilongtmyon.common.db.TableSrc;
import epsilongtmyon.common.db.TableSrcDao;
import epsilongtmyon.sandbox05.app.common.tx.SimpleManualTransaction;
import epsilongtmyon.sandbox05.app.common.tx.impl.DefaultSimpleManualTransactionStarter;

@Service
public class Sandbox05Service {

	private final PlatformTransactionManager txManager;

	private final DefaultSimpleManualTransactionStarter txStarter;

	private final TableSrcDao tableSrcDao;

	private final TableDestDao tableDestDao;

	public Sandbox05Service(
			PlatformTransactionManager txManager,
			DefaultSimpleManualTransactionStarter txStarter,
			TableSrcDao tableSrcDao,
			TableDestDao tableDestDao) {
		super();
		this.txManager = txManager;
		this.txStarter = txStarter;
		this.tableSrcDao = tableSrcDao;
		this.tableDestDao = tableDestDao;
	}

	public void execute() {

		List<TableSrc> tableSrcList = tableSrcDao.findAll();

		//executeInternal1(tableSrcList);
		executeInternal2(tableSrcList);
	}

	private void executeInternal1(List<TableSrc> tableSrcList) {

		for (TableSrc tableSrc : tableSrcList) {

			DefaultTransactionDefinition txDef = new DefaultTransactionDefinition();

			TransactionStatus txStatus = txManager.getTransaction(txDef);

			try {
				TableDest dest = new TableDest();
				dest.setId(tableSrc.getId());
				dest.setMessage(tableSrc.getMessage());
				tableDestDao.insert(dest);

				if (dest.getId().intValue() % 5 != 0) {
					txManager.commit(txStatus);
				} else {
					txManager.rollback(txStatus);
				}
			} catch (Exception e) {
				txManager.rollback(txStatus);
				e.printStackTrace();
			}
		}

	}

	private void executeInternal2(List<TableSrc> tableSrcList) {

		for (TableSrc tableSrc : tableSrcList) {

			final SimpleManualTransaction tx = txStarter.start();

			try {
				TableDest dest = new TableDest();
				dest.setId(tableSrc.getId());
				dest.setMessage(tableSrc.getMessage());
				tableDestDao.insert(dest);

				if (dest.getId().intValue() % 5 != 0) {
					tx.commit();
				} else {
					tx.rollback();
				}
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}
		}

	}
}
