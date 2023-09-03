package epsilongtmyon.lib;

import java.time.LocalDateTime;

/**
 * リクエストの日付を退避しておくもの
 * リクエストスコープで管理する。
 */
public class ControllerDateHolder {

	private LocalDateTime controllerDate;

	public void update() {
		if (this.controllerDate != null) {
			throw new IllegalStateException("既に設定済み");
		}
		this.controllerDate = LocalDateTime.now();
	}

	public LocalDateTime getControllerDate() {
		return controllerDate;
	}
}
