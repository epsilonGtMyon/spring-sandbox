package epsilongtmyon.common.db;

import java.io.Serializable;
import java.math.BigDecimal;

public class TableDest implements Serializable {

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
		return "TableDest [id=" + id + ", message=" + message + "]";
	}

}
