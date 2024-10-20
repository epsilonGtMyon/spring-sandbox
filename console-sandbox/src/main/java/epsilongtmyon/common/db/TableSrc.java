package epsilongtmyon.common.db;

import java.io.Serializable;
import java.math.BigDecimal;

public class TableSrc implements Serializable {

	private BigDecimal id;

	private String message;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TableSrc [id=" + id + ", message=" + message + "]";
	}

}
