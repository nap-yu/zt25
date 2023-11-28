package org.zt25.converter.bean;

import lombok.Data;
import org.zt25.converter.annotation.ZDataHandler;
import org.zt25.converter.enums.DataHandlerType;
import org.zt25.converter.plugin.mask.ChineseNameMaskHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class User<T> {
    private T id;
    //@PDataHandler(type = DataHandlerType.CUSTOM,handler = ChineseNameMaskHandler.class)
    @ZDataHandler(type = DataHandlerType.BASE64)
    private String name;
    private Character sex;

    private Integer age;
    private Date birth;
    private Boolean status;

    private UserCertificate<String> certificate;

    @ZDataHandler(key = "bbb", type = DataHandlerType.EMAIL)
    @ZDataHandler(key = "ccc", type = DataHandlerType.ADDRESS)
    @ZDataHandler(key = "eee", type = DataHandlerType.CUSTOM,handler = ChineseNameMaskHandler.class)
    private Map<String,Object> ext;

    @ZDataHandler(key = "bbb", type = DataHandlerType.EMAIL)
    @ZDataHandler(key = "ccc", type = DataHandlerType.ADDRESS)
    private List<Map<String,Object>> exts;

    private List<UserCertificate<String>> certificates;

}
