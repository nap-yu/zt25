package org.zt25.example.entity.commons;

import org.zt25.example.enums.CertificateType;
import org.zt25.persistence.orm.OrmEntity;

/**
 * @author : zhaotaiyu
 * @description : 证件
 * @date : 2020-09-07 22:56
 */
//@EqualsAndHashCode(callSuper = true)
//@Data
//@ToString(callSuper=true)
//@TableName("commons_certificate")
public class Certificate extends OrmEntity<Long> {

    /**
     * 证件号
     */
    private String no;

    /**
     * 证件类型
     */
    private CertificateType certificateType;

    /**
     * 有效期(开始)
     */
    private String effectiveStartDate;

    /**
     * 有效期(截止)
     */
    private String effectiveEndDate;

    /**
     * 发证机关
     */
    private String issuingAgency;

    /**
     * 关联表
     */
    private String correlationTable;

    /**
     * 关联外键
     */

    private Long fk;

    /**
     * 有效状态
     */
    private Boolean status;
}
