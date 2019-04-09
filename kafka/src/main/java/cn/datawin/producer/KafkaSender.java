package cn.datawin.producer;

import cn.datawin.bean.Message;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(String topic) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        String json  =JSON.toJSONString(message);
        System.out.println("+++++++++++++++++++++  message ="+json);
        kafkaTemplate.send(topic,json);
    }

    //发送消息方法
    public void send() {
        send("test");
    }
}