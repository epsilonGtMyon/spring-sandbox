package epsilongtmyon.shared;

/**
 * ユーザーエージェント
 */
public class UserAgent {

	public final String value;

	public UserAgent(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "UserAgent [value=" + value + "]";
	}

}
