package epsilongtmyon.app.endpoint.upload01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import epsilongtmyon.app.common.jackson.Base64Data;
import epsilongtmyon.app.common.tempdir.TempDirectoryHolder;
import epsilongtmyon.app.endpoint.upload01.spec.Base64DataRequest;
import epsilongtmyon.app.endpoint.upload01.spec.Base64RawTextRequest;
import epsilongtmyon.app.endpoint.upload01.spec.FormFileRequest;

@RestController
@RequestMapping("/upload01")
public class Upload01Controller {

	private static Logger logger = LoggerFactory.getLogger(Upload01Controller.class);

	private final TempDirectoryHolder tempDirectoryHolder;

	public Upload01Controller(TempDirectoryHolder tempDirectoryHolder) {
		super();
		this.tempDirectoryHolder = tempDirectoryHolder;
	}

	// MultipartFileで受ける
	// @ModelAttributeにする。
	@PostMapping("/formFile")
	public ResponseEntity<?> formFile(@ModelAttribute FormFileRequest request) throws IOException {

		MultipartFile file = request.getFile();
		Path tempPath = createTempPath();
		logger.info("保存先:{}", tempPath.toString());

		try (InputStream is = file.getInputStream();
				OutputStream os = Files.newOutputStream(tempPath);) {

			is.transferTo(os);
		}

		return ResponseEntity.accepted().build();
	}

	// JSONの中にbase64の文字列をそのまま受ける
	@PostMapping("/base64RawText")
	public ResponseEntity<?> base64RawText(@RequestBody Base64RawTextRequest request) throws IOException {

		Path tempPath = createTempPath();
		logger.info("保存先:{}", tempPath.toString());

		try (OutputStream os = Files.newOutputStream(tempPath);) {
			byte[] decoded = Base64.getDecoder().decode(request.getBase64Text());
			os.write(decoded);
		}

		return ResponseEntity.accepted().build();
	}

	// JSONの中にbase64の文字列をデシリアライズして受ける
	// そのためにカスタムデシリアライザを作る
	@PostMapping("/base64Data")
	public ResponseEntity<?> base64Data(@RequestBody Base64DataRequest request) throws IOException {
		Base64Data base64Data = request.getBase64Data();
		if (base64Data == null) {
			logger.info("ファイルないよ");
			return ResponseEntity.badRequest().build();
		}

		Path tempPath = createTempPath();
		logger.info("保存先:{}", tempPath.toString());

		try (OutputStream os = Files.newOutputStream(tempPath);) {
			byte[] decoded = base64Data.getData();
			os.write(decoded);
		}

		return ResponseEntity.accepted().build();
	}

	private Path createTempPath() {
		return tempDirectoryHolder.getTempDirectory().resolve("temp-" + System.currentTimeMillis() + ".dat")
				.toAbsolutePath();
	}

}
