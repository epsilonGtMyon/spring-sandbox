package app.section07.sandbox0702;

import java.util.concurrent.TimeUnit;

import org.springframework.core.task.TaskExecutor;

public class Sandbox0702TaskExecutorSample {


    private final TaskExecutor taskExecutor;

    public Sandbox0702TaskExecutorSample(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void printMessages() {
        for(int i = 0; i < 25; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }

    private static class MessagePrinterTask implements Runnable {

        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            System.out.println(message);
            try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}
