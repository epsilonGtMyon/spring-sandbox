package epsilongtmyon.app.endpoint.upload01.spec;

import org.springframework.web.multipart.MultipartFile;

public class FormFileRequest {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
