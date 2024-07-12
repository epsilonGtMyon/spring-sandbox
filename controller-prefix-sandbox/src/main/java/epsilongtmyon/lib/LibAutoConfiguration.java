package epsilongtmyon.lib;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

import epsilongtmyon.lib.mvc.LibMvcConfiguration;

@AutoConfiguration
@Import({
		LibMvcConfiguration.class
})
public class LibAutoConfiguration {

}
