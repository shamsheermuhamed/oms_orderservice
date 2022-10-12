package com.shopping.config;

import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaPublisherConfig {

	@Bean
	public ProducerFactory<String,Object> producerconfig()
	{
		HashMap<String, Object> configs=new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	@Bean
	public KafkaTemplate<String,Object> kafkatemplate(){
		
		return new KafkaTemplate<>(producerconfig());
		
	}
}
