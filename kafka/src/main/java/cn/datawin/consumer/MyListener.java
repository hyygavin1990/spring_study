package cn.datawin.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.util.CollectionUtils;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by hyygavin on 2018/9/10.
 */
public class MyListener {

    public static BlockingQueue<String> queue = new LinkedBlockingDeque<>();

    @KafkaListener(topics = {"audioserver_89"}, containerFactory = "batchFactory")
    public void listen(List<ConsumerRecord<?, ?> > records) throws InterruptedException {

        if(CollectionUtils.isEmpty(records)){
            System.out.println("队列还未有数据");
            return;
        }
        System.out.println("队列长度: " + records.size() + "");
        for (ConsumerRecord<?, ?> record : records) {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());

            if (kafkaMessage.isPresent()) {

                Object message = kafkaMessage.get();
                queue.put(message.toString());
//                System.out.println("queue put!"+message.toString());
//                System.out.println("----------------- record =" + record);
//                System.out.println("------------------ message =" + message);
            }
        }
        System.out.println("end");
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
