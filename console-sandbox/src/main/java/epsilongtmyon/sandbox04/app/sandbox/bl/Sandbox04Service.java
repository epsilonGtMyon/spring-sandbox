package epsilongtmyon.sandbox04.app.sandbox.bl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.sandbox04.app.common.template.SimpleDataFlowServiceTemplate;
import epsilongtmyon.sandbox04.app.sandbox.db.TableDest;
import epsilongtmyon.sandbox04.app.sandbox.db.TableSrc;

@Service
@Transactional
public class Sandbox04Service extends SimpleDataFlowServiceTemplate<Void, TableSrc, TableDest> {

	public Sandbox04Service(Sandbox04Operation operation) {
		super(operation);
	}

	public void execute() {
		executeTemplate(null);
	}
}
