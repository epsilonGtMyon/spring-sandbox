package epsilongtmyon.shared.messageconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class OneLineCsvMessageConverter implements HttpMessageConverter<OneLineCsv> {

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return List.of(new MediaType("text", "csv"), MediaType.ALL);
	}

	//-------
	//read
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return OneLineCsv.class.isAssignableFrom(clazz);
	}

	@Override
	public OneLineCsv read(Class<? extends OneLineCsv> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		InputStream is = inputMessage.getBody();
		try (InputStreamReader r = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader(r)) {
			OneLineCsv instance = clazz.getConstructor().newInstance();

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
		return OneLineCsv.class.isAssignableFrom(clazz);
	}

	@Override
	public void write(OneLineCsv t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		//ヘッダーの設定
		//AbstractHttpMessageConverterを見るともう少し 細かい方法でヘッダーを設定している。
		HttpHeaders headers = outputMessage.getHeaders();
		headers.setContentType(contentType);

		//ボディの設定
		OutputStream out = outputMessage.getBody();
		OutputStreamWriter w = new OutputStreamWriter(out, StandardCharsets.UTF_8);
		w.write(t.getValues().stream().collect(Collectors.joining(",")));
		w.flush();
	}

}
