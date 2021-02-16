package epsilongtmyon.shared.messageconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class OneLineCsvMessageConverter implements HttpMessageConverter<OneLineCsvAsignable> {

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return List.of(new MediaType("text", "csv"), MediaType.ALL);
	}

	//-------
	//read
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return OneLineCsvAsignable.class.isAssignableFrom(clazz);
	}

	@Override
	public OneLineCsvAsignable read(Class<? extends OneLineCsvAsignable> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		InputStream is = inputMessage.getBody();
		try (InputStreamReader r = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(r)) {
			OneLineCsvAsignable instance = clazz.getConstructor().newInstance();

			String[] split = br.readLine().split(",");
			for (int i = 0; i < split.length; i++) {
				instance.asignValue(i, split[i]);
			}

			return instance;
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	//-------
	//write
	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void write(OneLineCsvAsignable t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
