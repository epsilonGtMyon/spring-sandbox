package app.section02.sandbox0201;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

public class Sandbox0201Main {

	public static void main(String[] args) throws IOException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
			//DefaultResourceLoaderがデフォルトだと使われるみたい
			//getResourceを見ると/やclasspath: で始まるとクラスパスらしい
			//ProtocolResolver というものを使うと 独自のリソース読み込みの仕組みを追加できそう
			Resource resource = context.getResource("/app/section02/sandbox0201/resource.txt");
			try (InputStream is = resource.getInputStream()) {
				String text = inputStreamToString(is);
				System.out.println(text);
			}

		}

	}

	private static String inputStreamToString(InputStream is) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] bytes = bis.readAllBytes();

		String text = new String(bytes, StandardCharsets.UTF_8);
		return text;
	}
}
