package org.zt25.persistence.enums;


/**
 * 操作类型
 *
 * <p>
 * 根据操作类型在相应的数据操作时处理默认值
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public enum ResourceOperationType {
    /**
     * SQL中的插入操作
     */
    SQL_INSERT,

    /**
     * SQL中的删除操作
     */
    SQL_DELETE,

    /**
     * SQL中的更新操作
     */
    SQL_UPDATE,

    /**
     * SQL中的查询操作
     */
    SQL_SELECT;
}
