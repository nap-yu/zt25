package org.zt25.converter;

import cn.hutool.core.convert.Convert;
import org.junit.jupiter.api.Test;
import org.zt25.converter.bean.CertificateType;
import org.zt25.converter.bean.User;
import org.zt25.converter.bean.UserCertificate;
import org.zt25.converter.bean.UserDTO;
import org.zt25.converter.enums.SupportBeanConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class SupportBeanConverterTest {

    @Test
    void beanToBean() {

        List<UserCertificate<String>> certificates = new ArrayList<>();
        UserCertificate<String> certificate = new UserCertificate<String>();
        certificate.setId("1");
        certificate.setStatus(true);
        certificate.setNo("110101198112131018");
        certificate.setEffectiveStartDate(Convert.toDate("2000-1-1"));
        certificate.setEffectiveEndDate(Convert.toDate("2020-1-1"));
        certificate.setCertificateType(CertificateType.SFZ);
        certificates.add(certificate);

        //List<UserCertificate> certificates = new ArrayList<>();

        List<Map<String,Object>> exts = new ArrayList<>();
        Map<String,Object> ext = new HashMap<>();
        ext.put("aaa",1);
        ext.put("bbb","48130305@qq.com");
        ext.put("ccc","北京市东城区东直门外胡家园社区8-4-603");
        ext.put("ddd",certificate);
        ext.put("eee","赵太宇");

        exts.add(ext);

        User<String> user = new User<String>();
        user.setId("1");
        user.setName("张三");
        user.setAge(18);
        user.setSex('1');
        user.setStatus(true);
        user.setBirth(Convert.toDate("1981-12-13"));
        user.setCertificate(certificate);
        user.setExt(ext);
        user.setExts(exts);
        user.setCertificates(certificates);

        List<User<String>> users = new ArrayList<>();
        users.add(user);



        // 测试转换器
        UserDTO dto = (UserDTO) SupportBeanConverter.BEAN_TO_BEAN_HANDLER.convert(user, UserDTO.class);
        System.out.println(dto);

        List<UserDTO> dtos = (List<UserDTO>) SupportBeanConverter.TO_LIST_HANDLER.convert(users, UserDTO.class);
        System.out.println(dtos);
        //System.out.println(dto);
        //UserDTO dto1 = SupportBeanConverter.valueOf("BEAN_TO_BEAN").convert(user, UserDTO.class);
        //System.out.println(dto1);
    }
}
