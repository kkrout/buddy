package com.dong.buddy;

import java.util.concurrent.locks.LockSupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableZuulProxy
// @EnableFeignClients
//@EnableEurekaClient
//@EnableZuulServer
@SpringBootApplication
public class BuddyApplication
{
    public static void main(String[] args)
    {
        System.out.println(System.getProperty("java.version"));
        SpringApplication.run(BuddyApplication.class, args);
        // MyGateway gateway = context.getBean(MyGateway.class);
        // gateway.sendToMqtt("foo");
//        LockSupport.park();
    }

    @Bean
    // @LoadBalanced
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    // @Bean
    // public AccessCheckFilter accessCheckFilter()
    // {
    // return new AccessCheckFilter();
    // }

    // @Bean
    // public MessageChannel mqttInputChannel()
    // {
    // return new DirectChannel();
    // }
    //
    // @Bean
    // public MessageProducer inbound()
    // {
    // MqttPahoMessageDrivenChannelAdapter adapter = new
    // MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883",
    // "testClient", "topic1", "topic2");
    // adapter.setCompletionTimeout(5000);
    // adapter.setConverter(new DefaultPahoMessageConverter());
    // adapter.setQos(1);
    // adapter.setOutputChannel(mqttInputChannel());
    // return adapter;
    // }
    //
    // @Bean
    // @ServiceActivator(inputChannel = "mqttInputChannel")
    // public MessageHandler handler()
    // {
    // return new MessageHandler()
    // {
    //
    // @Override
    // public void handleMessage(Message<?> message) throws MessagingException
    // {
    // System.out.println(message.getPayload());
    // }
    //
    // };
    // }

}
