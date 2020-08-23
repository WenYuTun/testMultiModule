package com.supermap.testMultiModule.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyt
 * @description: 测试kafka
 * @date: 2019/12/10
 * @version: 1.0
 */
//@RestController(value = "/kafka")
public class KafkaTestController {

//    private static final Logger LOG = LoggerFactory.getLogger(KafkaTestController.class);
//
//    @Autowired
//    private KafkaTemplate<Object,Object> kafkaTemplate;
//
//    @GetMapping("/send/{input}")
//    public void sendFoo(@PathVariable String input) {
//        kafkaTemplate.send("topic_input", input);
//    }
//
//    @KafkaListener(id = "webGroup", topics = "topic_input")
//    public void listen(String input) {
//        LOG.info("input value: {}" , input);
//    }


}
