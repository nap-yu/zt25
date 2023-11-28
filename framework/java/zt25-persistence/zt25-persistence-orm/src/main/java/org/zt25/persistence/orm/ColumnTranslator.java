package org.zt25.persistence.orm;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

/**
 * Orm 实体属性名和数据库列名转换帮助类
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
@Slf4j
public class ColumnTranslator {

    /**
     * 根据根据实体属性名找到数据库表列名。
     *
     * <p>
     * 如果没有找到对应的映射，则会把属性直接作为列名返回。
     * </p>
     *
     * @param entityType   实体的类型
     * @param propertyName 属性名
     * @return 列名
     */
    public static String translatePropertyToColumn(Class<?> entityType, String propertyName) {
        return translate(entityType, propertyName, field -> {
            if (propertyName.equals(field.getProperty())) {
                log.debug("Column found: {}. Entity type: {}, property name: {}", field.getColumn(), entityType.getName(), propertyName);
                return field.getColumn();
            }
            return null;
        });
    }

    /**
     * 根据数据库表列名找到实体属性名。
     *
     * <p>
     * 如果没有找到对应的映射，则会把列名直接作为属性名返回。
     * </p>
     *
     * @param entityType 实体的类型
     * @param columnName 列名
     * @return 属性名
     */
    public static String translateColumnToProperty(Class<?> entityType, String columnName) {
        return translate(entityType, columnName, field -> {
            if (columnName.equals(field.getColumn())) {
                log.debug("Property found: {}. Entity type: {}, column name: {}", field.getProperty(), entityType.getName(), columnName);
                return field.getProperty();
            }
            return null;
        });
    }

    private static String translate(Class<?> entityType, String originalName, Function<TableFieldInfo, String> function) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityType);
        if (tableInfo == null || CharSequenceUtil.isEmpty(originalName)) {
            log.debug("Table info is not found or originalName is null. Entity type is {}, originalName is {}", entityType.getName(), originalName);
            return originalName;
        }
        log.debug("Entity type is {}, table name is: {}, originalName is {}", entityType.getName(), tableInfo.getTableName(), originalName);
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        for (TableFieldInfo field : fieldList) {
            String target = function.apply(field);
            if (target != null) {
                return target;
            }
        }
        return originalName;
    }
}
