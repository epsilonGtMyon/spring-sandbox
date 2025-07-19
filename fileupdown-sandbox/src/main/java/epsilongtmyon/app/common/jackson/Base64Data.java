package epsilongtmyon.app.common.jackson;

import java.io.Serializable;

/**
 * デシリアライズしたBase64を受け取るクラス
 */
public class Base64Data implements Serializable {

	private final byte[] data;

	public Base64Data(byte[] data) {
		super();
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}

}
