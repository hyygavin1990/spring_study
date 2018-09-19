package cn.datawin.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by hyygavin on 2018/9/10.
 */
public class MyListener {

    @KafkaListener(topics = {"zhisheng"}, containerFactory = "batchFactory")
    public void listen(List<ConsumerRecord<?, ?> > records) {

        if(CollectionUtils.isEmpty(records)){
            System.out.println("队列还未有数据");
            return;
        }
        System.out.println("队列长度: " + records.size() + "");
        List<String> messageList = new ArrayList<>();
        for (ConsumerRecord<?, ?> record : records) {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());

            if (kafkaMessage.isPresent()) {

                Object message = kafkaMessage.get();
                messageList.add(message.toString());
//                System.out.println("----------------- record =" + record);
//                System.out.println("------------------ message =" + message);
            }
        }
        System.out.println(JSON.toJSONString(messageList));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
