package org.zt25.converter.bean;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class UserDTO {

    private String id;
    @JSONField(name = "name")
    private String userName;
    private Character sex;
    private Integer age;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date birth;
    private Boolean status;

    private UserCertificate<String> certificate;

    private Map<String,Object> ext;

    private List<Map<String,Object>> exts;

    private List<UserCertificate<String>> certificates;

    //private Map<String,Object> certificate;

    //public void setUserName(String userName) {
    //    this.userName = "这是"+userName;
    //}
}
