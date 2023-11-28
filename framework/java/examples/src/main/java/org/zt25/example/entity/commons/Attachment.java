package org.zt25.example.entity.commons;

import org.zt25.persistence.orm.OrmEntity;

/**
 * @author : zhaotaiyu
 * @description : 附件
 * @date : 2020-09-07 22:56
 */
//@EqualsAndHashCode(callSuper = true)
//@Data
//@ToString(callSuper=true)
//@TableName("commons_attachment")
public class Attachment extends OrmEntity<Long> {

    /**
     * 证件号
     */
    private String fileName;

    /**
     * 附件路径
     */
    private String filePath;

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
