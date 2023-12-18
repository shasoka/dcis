package ru.shasoka.dcis.prac_8.config;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class JmsTemplateConfiguration {

  @Bean
  public ConnectionFactory connectionFactory() {
    return new ActiveMQConnectionFactory("tcp://localhost:61616");
  }

  @Bean
  @Scope("prototype")
  public JmsTemplate jmsTemplate() {
      return new JmsTemplate(connectionFactory());
  }

}
