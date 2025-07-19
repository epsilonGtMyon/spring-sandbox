package epsilongtmyon.app.common.tempdir;

import java.io.BufferedWriter;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TempDirectoryHolder implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(TempDirectoryHolder.class);

	private Path tempDirectory;

	private Path dummyDatFile;

	private Path dummyTextFile;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.tempDirectory = Files.createTempDirectory("my-app-temp");
		logger.info("tempDirectory={}", tempDirectory.toString());

		dummyDatFile = tempDirectory.resolve("2MBファイル.dat");
		try (RandomAccessFile raf = new RandomAccessFile(dummyDatFile.toFile(), "rw");) {
			raf.setLength(2 * 1024 * 1024);
		}
		logger.info("ダミーdatファイルを作成しました。 {}", dummyDatFile.toAbsolutePath().toString());
		
		
		dummyTextFile = tempDirectory.resolve("テキスト.txt");
		try(BufferedWriter bw = Files.newBufferedWriter(dummyTextFile, StandardCharsets.UTF_8)) {
			bw.write("テストデータ");
		}
		logger.info("ダミーtextファイルを作成しました。 {}", dummyTextFile.toAbsolutePath().toString());
		
	}

	public Path getTempDirectory() {
		return tempDirectory;
	}

	public Path getDummyDatFile() {
		return dummyDatFile;
	}

	public Path getDummyTextFile() {
		return dummyTextFile;
	}

}
