package ma.munisys.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import ma.munisys.model.Notification;

@Configuration
@EnableScheduling
public class NotificationScheduler {
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	 //@Scheduled(fixedRate = 100000)
	 public void publishUpdates(){
		  /*messagingTemplate.convertAndSendToUser(
			      "mbouhafs", 
			      "/topic/greetings", 
			      new Notification("teste mbouhafs")
			    );*/
		 
		 try {
			greeting(new Notification("teste mbouhafs") );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 
	 @MessageMapping("/hello")
	 @SendTo("/topic/greetings")
	 public void greeting(Notification message) throws Exception {
		
		 messagingTemplate.convertAndSend("/topic/greetings", new Notification("teste mbouhafs"));
	 }

}
