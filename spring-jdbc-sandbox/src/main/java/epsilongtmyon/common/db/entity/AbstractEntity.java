package epsilongtmyon.common.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AbstractEntity implements Serializable {

	protected Timestamp createdAt;

	protected Timestamp updatedAt;

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
