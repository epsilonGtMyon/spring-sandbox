package epsilongtmyon.app.sandbox.sandbox01;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.common.mybatis.entity.MyTable;
import epsilongtmyon.app.common.mybatis.mapper.MyTableMapper;

// とりあえず普通に使う

@Service
@Transactional
public class Sandbox01Service {

	private final MyTableMapper myTableMapper;

	public Sandbox01Service(MyTableMapper myTableMapper) {
		super();
		this.myTableMapper = myTableMapper;
	}

	public void execute() {
		List<MyTable> myTables = myTableMapper.findAll();
		myTables.forEach(System.out::println);
	}

}
