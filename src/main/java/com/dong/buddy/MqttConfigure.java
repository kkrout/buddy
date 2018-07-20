// package com.dong.buddy;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.integration.annotation.MessagingGateway;
// import org.springframework.integration.channel.DirectChannel;
// import org.springframework.integration.dsl.IntegrationFlow;
// import org.springframework.integration.dsl.IntegrationFlows;
// import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
// import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
// import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
// import org.springframework.messaging.MessageChannel;
// import org.springframework.messaging.MessageHandler;
//
// @Configuration
// public class MqttConfigure
// {
//
// @Bean
// public MqttPahoClientFactory mqttClientFactory()
// {
// DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
// factory.setServerURIs("tcp://localhost:1883");
// factory.setUserName("admin");
// factory.setPassword("admin");
// return factory;
// }
//
// @Bean
// public IntegrationFlow mqttOutFlow()
// {
// return IntegrationFlows.from(outChannel()).handle(mqttOutbound()).get();
// }
//
// @Bean
// public MessageChannel outChannel()
// {
// return new DirectChannel();
// }
//
// @Bean
// public MessageHandler mqttOutbound()
// {
// MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("siSamplePublisher",
// mqttClientFactory());
// messageHandler.setAsync(true);
// messageHandler.setDefaultTopic("siSampleTopic");
// return messageHandler;
// }
//
// @MessagingGateway(defaultRequestChannel = "outChannel")
// public interface MsgWriter
// {
// void write(String note);
// }
//
// }
