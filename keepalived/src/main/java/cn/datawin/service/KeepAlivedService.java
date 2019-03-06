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
public class KeepAlivedService {

    public String exec(String command ) throws Exception {


        String[] cmdArr = { "systemctl", command,"keepalived"};
        Process ps = Runtime.getRuntime().exec(cmdArr);
        int wait = ps.waitFor();
        String cmdStr = String.join(" ",Arrays.asList(cmdArr));
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
