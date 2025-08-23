package epsilongtmyon.app.sandbox.sandbox02;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.common.mybatis.entity.MyTable;
import epsilongtmyon.app.sandbox.sandbox02.mapper.Sandbox02Mapper;

@Service
@Transactional
public class Sandbox02Service {

	private final Sandbox02Mapper mapper;

	public Sandbox02Service(Sandbox02Mapper mapper) {
		super();
		this.mapper = mapper;
	}

	public void execute() {
		List<MyTable> myTables = mapper.findAll();
		myTables.forEach(System.out::println);
	}

}
