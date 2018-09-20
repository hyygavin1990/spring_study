package cn.datawin.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hyygavin on 2018/9/20.
 */
@Service
public class KafkaService {
    public List<List<String>> getDataList(String bootstrapServer, String groupId) {
        String result ="";
        try {
            //docker run kafka-docker_kafka kafka-consumer-groups.sh --bootstrap-server 61.160.248.169:9092 --group log-consumer-group --describe
            String[] cmd = { "docker", "run","kafka-docker_kafka","kafka-consumer-groups.sh",
                    "--bootstrap-server",bootstrapServer,"--group",groupId,"--describe"};
            result = exec(cmd);
            //docker rm $(docker ps -a | grep kafka-docker_kafka_ob | grep Exited | awk '{print $1}')
            String[] cmd1 = { "docker", "ps","-a"};
            String result1 = exec(cmd1);
            List<String> list = Arrays.asList(result1.split("\n"));
            List<String> list1 = list.stream().filter(i -> i.contains("kafka-docker_kafka") && i.contains("Exited")).map(i -> i.split("\\s+")[0])
                    .collect(Collectors.toList());
            for (String s : list1) {
                String[] cmd2 = { "docker", "rm",s};
                exec(cmd2);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        result = result.trim();

        List<List<String>> datasList = new ArrayList<>();

        String[] arr = result.split("\n");
        for (String s : arr) {
            datasList.add(Arrays.asList(s.split("\\s+")));
        }
        return datasList;

    }

    public String exec(String[] cmd ) throws Exception {
        Process ps = Runtime.getRuntime().exec(cmd);
        int wait = ps.waitFor();
        String cmdStr = String.join(" ",Arrays.asList(cmd));
        if(wait==0){
            System.out.println(cmdStr+"===执行成功");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        String result = sb.toString();
        System.out.println(cmdStr+"===执行结果");
        System.out.println(result);
        return result;

    }
}
