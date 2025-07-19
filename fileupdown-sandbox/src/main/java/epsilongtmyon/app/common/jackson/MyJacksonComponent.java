package epsilongtmyon.app.common.jackson;

import java.io.IOException;
import java.util.Base64;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@JsonComponent
public class MyJacksonComponent {

	public static class Base64DataDeserializer extends JsonDeserializer<Base64Data> {

		@Override
		public Base64Data deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

			String base64 = p.getText();
			if (base64 == null) {
				return null;
			}

			return new Base64Data(Base64.getDecoder().decode(base64));
		}

	}
}
