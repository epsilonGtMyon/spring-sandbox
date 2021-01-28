package epsilongtmyon.returnvaluesandbox.app.sandbox03;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

import javax.imageio.ImageIO;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sandbox03")
public class Sandbox03Controller {

	@GetMapping
	public String index() {
		return "sandbox03/index";
	}

	/*
	 * ハンドラーが解決できない
	 * java.lang.IllegalArgumentException: Unknown return value type:
	 */
	@GetMapping("image01")
	public byte[] image01() {
		byte[] body = genImage();
		return body;
	}

	/*
	 * RequestResponseBodyMethodProcessorが使われて
	 * ByteArrayHttpMessageConverter が使われる
	 * @return
	 */
	@GetMapping("image02")
	@ResponseBody
	public byte[] image02() {
		byte[] body = genImage();
		return body;
	}

	/*
	 * HttpEntityMethodProcessorがハンドラーで
	 * ByteArrayHttpMessageConverter が使われる
	 */
	@GetMapping("image03")
	public ResponseEntity<byte[]> image03() {
		byte[] body = genImage();
		return ResponseEntity.ok(body);
	}

	/*
	 * ServletModelAttributeMethodProcessorがハンドラーで
	 *
	 * org.thymeleaf.exceptions.TemplateInputException: Error resolving template [sandbox03/image04],
	 * が発生する
	 */
	@GetMapping("image04")
	public Resource image04() {
		byte[] body = genImage();
		return new ByteArrayResource(body);
	}

	/*
	 * HttpEntityMethodProcessorがハンドラーで
	 * ResourceHttpMessageConverterが使われる
	 */
	@GetMapping("image05")
	@ResponseBody
	public Resource image05() {
		byte[] body = genImage();
		return new ByteArrayResource(body);
	}

	/*
	 * HttpEntityMethodProcessorがハンドラーで
	 * ResourceHttpMessageConverterが使われる
	 */
	@GetMapping("image06")
	public ResponseEntity<Resource> image06() {
		byte[] body = genImage();
		return ResponseEntity.ok(new ByteArrayResource(body));
	}

	private byte[] genImage() {

		BufferedImage img = new BufferedImage(120, 40, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = img.getGraphics();
		g.setColor(new Color(0, 200, 0));
		g.fillRect(0, 0, 120, 40);
		g.setColor(Color.WHITE);
		g.drawString("画像です。", 15, 15);
		g.dispose();

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

			ImageIO.write(img, "png", baos);
			baos.flush();
			return baos.toByteArray();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
