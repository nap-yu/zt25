package org.zt25.persistence.orm;

import org.apache.ibatis.mapping.SqlCommandType;
import org.zt25.persistence.enums.ResourceOperationType;

/**
 * 自定义sql操作类型和mybatis的sql操作类型转换
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public class SqlCommandTypeTranslator {

    /**
     * 转换类型
     * @param type 操作类型 {@link SqlCommandType}
     * @return {@link ResourceOperationType}
     */
    public static ResourceOperationType translate(SqlCommandType type) {
        switch (type) {
            case INSERT:
                return ResourceOperationType.SQL_INSERT;
            case DELETE:
                return ResourceOperationType.SQL_DELETE;
            case UPDATE:
                return ResourceOperationType.SQL_UPDATE;
            case SELECT:
                return ResourceOperationType.SQL_SELECT;
            default:
                return null;
        }
    }
}
