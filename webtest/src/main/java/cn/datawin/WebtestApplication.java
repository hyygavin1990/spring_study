package cn.datawin;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RestController
public class WebtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebtestApplication.class, args);
	}

	/*@RequestMapping(value = "/cc", method = RequestMethod.GET)
	public String cc() throws IOException {
		FileUtils.write(new File("/mydata/logs/xjar.key"),"password:8ik,$RFV^YHN2wsx");
		return "ok";
	}
	@RequestMapping(value = "/dc", method = RequestMethod.GET)
	public String dc() throws IOException {
		new File("/mydata/logs/xjar.key").delete();
		return "ok";
	}
	@RequestMapping(value = "/cch", method = RequestMethod.GET)
	public String cch() throws IOException {
		FileUtils.write(new File("/mydata/logs/xjar.key"),"password:8ik,$RFV^YHN2wsx\r\nhold:1");
		return "ok";
	}*/
	@RequestMapping("/translateCorrect")
	public JSONObject translateCorrect( String text){

		JSONObject result = new JSONObject();
		result.put("modifyCallText",text);
		result.put("info","");
		result.put("idInfo", Collections.emptyList());
		result.put("correctInfo","");
		return result;
	}
	@RequestMapping("/designationCorrect")
	public JSONObject designationCorrect( String text){

		JSONObject result = new JSONObject();
		result.put("modifyCallText",text);
		result.put("info","");
		result.put("idInfo", Collections.emptyList());
		result.put("correctInfo","");
		return result;
	}
	@RequestMapping("/nameCorrect")
	public JSONObject nameCorrect( String text){

		JSONObject result = new JSONObject();
		result.put("modifyCallText",text);
		result.put("info","");
		result.put("idInfo", Collections.emptyList());
		result.put("correctInfo","");
		return result;
	}

}
