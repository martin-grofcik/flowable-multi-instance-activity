package parallel.flowable.test;

import java.io.Serializable;

public class Message implements Serializable {
	private String message = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public int hashCode() {
		return message.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Message)) {
			return false;
		}
		Message t = (Message) obj;
		return t.message.equals(message);
	}
}
