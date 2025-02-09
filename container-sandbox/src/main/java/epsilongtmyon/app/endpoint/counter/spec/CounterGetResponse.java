package epsilongtmyon.app.endpoint.counter.spec;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CounterGetResponse {

	private int count = 0;

	private List<LocalDateTime> accessTimes = new ArrayList<>();

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
