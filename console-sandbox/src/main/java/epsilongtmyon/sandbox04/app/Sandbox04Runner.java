package epsilongtmyon.sandbox04.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox04.app.sandbox.bl.Sandbox04Service;
import epsilongtmyon.sandbox04.app.sandbox.db.TableDestDao;

@Component
public class Sandbox04Runner implements ApplicationRunner {

	private final Sandbox04Service service;

	private final TableDestDao tableDestDao;

	public Sandbox04Runner(Sandbox04Service service, TableDestDao tableDestDao) {
		super();
		this.service = service;
		this.tableDestDao = tableDestDao;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			service.execute();
		} catch (RuntimeException ex) {
			// 例外おきたら何もしない(検証用)
			ex.printStackTrace();
		}

		// 状況表示
		System.out.println("---print TABLE_DEST---");
		tableDestDao.findAll().forEach(System.out::println);
	}

}
