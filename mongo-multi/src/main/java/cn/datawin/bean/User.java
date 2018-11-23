package cn.datawin.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "user")
public class User  {

    @Id
    private String id;
    private String name ;
    private String gender;
    private Date inittime;

}