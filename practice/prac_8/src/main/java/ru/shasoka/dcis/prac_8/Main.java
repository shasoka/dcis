package ru.shasoka.dcis.prac_8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.shasoka.dcis.prac_8.messages.MessageReceiver;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
		MessageReceiver messageReceiver = context.getBean(MessageReceiver.class);
		messageReceiver.startReceivingMessages();
	}

}
