package epsilongtmyon.sandbox.sandbox01.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.common.db.entity.Emp;
import epsilongtmyon.sandbox.sandbox01.app.repository.Sandbox01Dao;

@Service
@Transactional
public class Sandbox01Service {

	private final Sandbox01Dao sandbox01Dao;

	public Sandbox01Service(Sandbox01Dao sandbox01Dao) {
		super();
		this.sandbox01Dao = sandbox01Dao;
	}

	public void execute() {

		Emp emp = sandbox01Dao.findById("E0001");
		System.out.println(emp);

	}
}
