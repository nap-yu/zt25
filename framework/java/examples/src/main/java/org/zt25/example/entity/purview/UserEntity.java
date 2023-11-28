package org.zt25.example.entity.purview;

import org.zt25.example.entity.commons.Certificate;
import org.zt25.persistence.orm.OrmEntity;

/**
 * @author : zhaotaiyu
 * @description : 用户信息
 * @date : 2020-09-07 22:56
 */
//@EqualsAndHashCode(callSuper = true)
//@Data
//@ToString(callSuper=true)
//@TableName("purview_user")
public class UserEntity extends OrmEntity<Long> {

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 英文名
     */
    private String enName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 最高学历
     */
    private String eduLevel;

    /**
     * 电话
     */
    private String phone;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 签章
     */
    private String signature;

    /**
     * 证件类型
     */
    private Certificate certificates;


}
