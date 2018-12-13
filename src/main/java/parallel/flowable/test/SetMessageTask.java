package parallel.flowable.test;

import java.util.ArrayList;
import java.util.List;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SetMessageTask implements JavaDelegate {

	
    @Override
    public void execute(DelegateExecution context) {
        List<Message> messages = new ArrayList<Message>();
        for (int i = 0; i < 3; i++) {
        	Message m = new Message();
        	m.setMessage("" + i);
            messages.add(m);
        }
        System.out.println("Setting messages " + messages);
        context.setVariable("messages", messages);
    }
}
