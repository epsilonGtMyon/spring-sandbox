package epsilongtmyon.sandbox02.app.common.exec;

public class BatchMetadata {

	private String batchId;

	private String batchBeanName;

	private boolean scheduled;

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchBeanName() {
		return batchBeanName;
	}

	public void setBatchBeanName(String batchBeanName) {
		this.batchBeanName = batchBeanName;
	}

	public boolean isScheduled() {
		return scheduled;
	}

	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}

	@Override
	public String toString() {
		return "BatchMetadata [batchId=" + batchId + ", batchBeanName=" + batchBeanName + ", scheduled=" + scheduled
				+ "]";
	}

}
