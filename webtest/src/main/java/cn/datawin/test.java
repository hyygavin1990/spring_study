package cn.datawin;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by hyygavin on 2020/5/12.
 */
public class test {
    public static void main(String[] args) throws IOException {
        FileUtils.write(new File("/mydata/logs/xjar.key"),"password:8ik,$RFV^YHN2wsx\r\nhold:1");
    }
}
