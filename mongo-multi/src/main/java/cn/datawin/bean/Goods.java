package cn.datawin.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "goods")
public class Goods {

    @Id
    private String id;
    private String name ;
    private Double price;

}