package epsilongtmyon.app.endpoint.download01;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.springframework.core.io.PathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.common.tempdir.TempDirectoryHolder;

@RestController
@RequestMapping("/download01")
public class Download01Controller {

	private final TempDirectoryHolder tempDirectoryHolder;

	public Download01Controller(TempDirectoryHolder tempDirectoryHolder) {
		super();
		this.tempDirectoryHolder = tempDirectoryHolder;
	}

	@GetMapping("/datFile")
	public ResponseEntity<?> datFile() {
		Path path = tempDirectoryHolder.getDummyDatFile();
		return toResponse(path);
	}

	@GetMapping("/textFile")
	public ResponseEntity<?> textFile() {
		Path path = tempDirectoryHolder.getDummyTextFile();
		return toResponse(path);
	}

	private ResponseEntity<?> toResponse(Path path) {
		PathResource pr = new PathResource(path);

		return ResponseEntity
				.ok()

				.headers(headers -> {
					// content-typeの内容を取得
					MediaType mediaType = MediaTypeFactory.getMediaType(pr).orElse(MediaType.APPLICATION_OCTET_STREAM);
					headers.setContentType(mediaType);
					// ----------------------------------
					// content-disposition
					ContentDisposition cd = ContentDisposition
							.attachment() // location.hrefの時にinlineだとcontent-typeによっては直接ファイル開いたりする。ajaxの時は関係ないはず
							.filename(path.getFileName().toString(), StandardCharsets.UTF_8)
							.build();
					headers.setContentDisposition(cd);
				})
				.body(pr);
	}

}
