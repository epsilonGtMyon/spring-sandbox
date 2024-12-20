package epsilongtmyon.sandbox04.app.sandbox.bl;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import epsilongtmyon.common.db.TableDest;
import epsilongtmyon.common.db.TableDestDao;
import epsilongtmyon.common.db.TableSrc;
import epsilongtmyon.common.db.TableSrcDao;
import epsilongtmyon.sandbox04.app.common.template.SimpleDataFlowServiceOperation;

@Component
public class Sandbox04Operation implements SimpleDataFlowServiceOperation<Void, TableSrc, TableDest> {

	private final TableSrcDao tableSrcDao;

	private final TableDestDao tableDestDao;

	public Sandbox04Operation(TableSrcDao tableSrcDao, TableDestDao tableDestDao) {
		super();
		this.tableSrcDao = tableSrcDao;
		this.tableDestDao = tableDestDao;
	}

	@Override
	public Stream<TableSrc> readAsStream(Void param) {
		return tableSrcDao.findAllAsStream();
	}

	@Override
	public TableDest process(TableSrc readItem, Void param) {
		TableDest dest = new TableDest();
		dest.setId(readItem.getId());
		dest.setMessage(readItem.getMessage());
		return dest;
	}

	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void writeChunk(List<TableDest> writeItems, Void param) {
		for (TableDest dest : writeItems) {
			tableDestDao.insert(dest);

			if (dest.getId().intValue() > 38) {
				throw new RuntimeException("例外起こす");
			}
		}
	}

}
