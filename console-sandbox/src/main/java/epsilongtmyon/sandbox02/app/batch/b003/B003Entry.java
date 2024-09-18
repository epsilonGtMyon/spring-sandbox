package epsilongtmyon.sandbox02.app.batch.b003;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox02.app.common.exec.Batch;

@Batch("B003")
@Component
public class B003Entry {

	
	@Scheduled(fixedDelay = 1000L)
	public void execute() {
		
		
		System.out.println("B003Entry");
	}
}
