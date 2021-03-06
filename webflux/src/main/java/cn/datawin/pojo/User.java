package cn.datawin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
        @Id
        private String id;
        @Indexed(unique = true) // 注解属性username为索引，并且不能重复
        private String username;
        private String phone;
        private String email;
        private String name;
        private Date birthday;
    }