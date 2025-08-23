package epsilongtmyon.app.sandbox.sandbox03;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epsilongtmyon.app.common.mybatis.entity.MyException;
import epsilongtmyon.app.common.mybatis.mapper.MyExceptionMapper;

// 例外は MyBatisExceptionTranslatorによりSpringの例外でラップされたものがスローされるようになっている。

@Service
@Transactional
public class Sandbox03Service {

	private static final Logger logger = LoggerFactory.getLogger(Sandbox03Service.class);

	private final MyExceptionMapper myExceptionMapper;

	public Sandbox03Service(MyExceptionMapper myExceptionMapper) {
		super();
		this.myExceptionMapper = myExceptionMapper;
	}

	public void execute() {
		doNotNull();
		doDuplicateKey();
		doInvalidScale();
	}

	private void doNotNull() {
		logger.info("doNotNull");
		try {
			MyException keyNull = new MyException();
			myExceptionMapper.insert(keyNull);
		} catch (Exception e) {
			logger.error("例外", e);
		}
	}

	private void doDuplicateKey() {
		logger.info("doDuplicateKey");
		try {
			MyException duplicateKey = new MyException();
			duplicateKey.setExKey("EX001");
			myExceptionMapper.insert(duplicateKey);
		} catch (Exception e) {
			logger.error("例外", e);
		}
	}

	private void doInvalidScale() {
		logger.info("doInvalidScale");
		try {
			MyException invalidScale = new MyException();
			invalidScale.setExKey("EX002");
			invalidScale.setAmount(new BigDecimal("12345678901.123"));
			myExceptionMapper.insert(invalidScale);
		} catch (Exception e) {
			logger.error("例外", e);
		}
	}
}
