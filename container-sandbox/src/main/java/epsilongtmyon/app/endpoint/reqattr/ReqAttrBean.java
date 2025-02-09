package epsilongtmyon.app.endpoint.reqattr;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReqAttrBean implements Serializable {

	private int count = 0;

	private List<LocalDateTime> accessTimes = new ArrayList<>();

	public int countUp() {
		count++;
		return count;
	}

	public void addAccessTimes() {
		accessTimes.add(LocalDateTime.now());
	}

	public void access() {
		countUp();
		addAccessTimes();
	}

	//----------------------------

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<LocalDateTime> getAccessTimes() {
		return accessTimes;
	}

	public void setAccessTimes(List<LocalDateTime> accessTimes) {
		this.accessTimes = accessTimes;
	}
}
