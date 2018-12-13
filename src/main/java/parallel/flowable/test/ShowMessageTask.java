package parallel.flowable.test;

import java.util.List;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class ShowMessageTask implements JavaDelegate {

	
    @Override
    public void execute(DelegateExecution context) {

        List<Message> messages = (List<Message>) context.getVariable("messages");
        System.out.println("NEW messages: " + messages);
    }
}
