package app.section02.sandbox0203;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Sandbox0203Hoge {

	private final Resource resource;

	public Sandbox0203Hoge(@Value("${resourcePath}") Resource resource) {
		this.resource = resource;
	}

	public void hello() {

		try (InputStream is = resource.getInputStream()) {
			String text = inputStreamToString(is);
			System.out.println(text);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

	}

	private static String inputStreamToString(InputStream is) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] bytes = bis.readAllBytes();

		String text = new String(bytes, StandardCharsets.UTF_8);
		return text;
	}

}
