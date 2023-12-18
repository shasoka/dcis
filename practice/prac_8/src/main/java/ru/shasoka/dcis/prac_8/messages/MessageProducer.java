package ru.shasoka.dcis.prac_8.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

  private final JmsTemplate jmsTemplate;
  private final String queueName;

  @Autowired
  public MessageProducer(JmsTemplate jmsTemplate, @Value("${queue.name}") String queueName) {
    this.jmsTemplate = jmsTemplate;
    this.queueName = queueName;
  }

  public void sendMessage(String message) {
    jmsTemplate.convertAndSend(queueName, message);
  }

}
