package epsilongtmyon.sandbox05.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import epsilongtmyon.common.db.TableDestDao;
import epsilongtmyon.sandbox05.app.bl.Sandbox05Service;

@Component
public class Sandbox05Runner implements ApplicationRunner {

	private final Sandbox05Service service;

	private final TableDestDao tableDestDao;

	public Sandbox05Runner(Sandbox05Service service, TableDestDao tableDestDao) {
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