package epsilongtmyon.returnvaluesandbox.app.sandbox02;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.returnvaluesandbox.app.sandbox02.Sandbox02Controller.Sandbox0202Exception;

@ControllerAdvice
public class Sandbox02ControllerAdvice {



	@ExceptionHandler({ Sandbox0202Exception.class })
	public ModelAndView Aaa(Sandbox0202Exception ex) {
		Thread.dumpStack();

		try (StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw)) {
			ex.printStackTrace(pw);
			pw.flush();

			ModelAndView mav = new ModelAndView("sandbox02/index");
			mav.addObject("ex", sw.toString());

			return mav;

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
