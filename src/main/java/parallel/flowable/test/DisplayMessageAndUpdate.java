package parallel.flowable.test;

import java.util.List;
import java.util.concurrent.Phaser;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class DisplayMessageAndUpdate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution context) {
		List<Message> messages = (List<Message>) context.getVariable("messages");
		Message message = (Message) context.getVariable("message");
		Message newMessage = new Message();
		newMessage.setMessage(message.getMessage() + "Changed");
		messages.set(messages.indexOf(message), newMessage);
		context.setVariable("messages", messages);
		System.out.println("Updating messages: " + messages);
	}

}
