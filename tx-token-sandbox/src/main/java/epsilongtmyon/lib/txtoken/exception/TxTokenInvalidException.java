package epsilongtmyon.lib.txtoken.exception;

/**
 * トランザクショントークンが不正である事を表す例外
 */
public class TxTokenInvalidException extends RuntimeException {

	public TxTokenInvalidException() {
		super();
	}

	public TxTokenInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public TxTokenInvalidException(String message) {
		super(message);
	}

	public TxTokenInvalidException(Throwable cause) {
		super(cause);
	}

}
