package org.zt25.persistence.orm.enums;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.yulichang.query.MPJQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.zt25.persistence.orm.ColumnTranslator;
import org.zt25.persistence.orm.annotation.Join;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum JoinMode {

    LEFT{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper,Map<String, String> joinModeMap) {
            wrapper.leftJoin(StrUtil.format("{tableName} {alias} ON {alias}.{joinColumn} = {thisTableName}.{thisColumn}",joinModeMap));
        }
    },
    RIGHT{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper,Map<String, String> joinModeMap) {
            wrapper.rightJoin(StrUtil.format("{tableName} {alias} ON {alias}.{joinColumn} = {thisTableName}.{thisColumn}",joinModeMap));
        }
    },
    INNER{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper,Map<String, String> joinModeMap) {
            wrapper.innerJoin(StrUtil.format("{tableName} {alias} ON {alias}.{joinColumn} = {thisTableName}.{thisColumn}",joinModeMap));
        }
    },
    FULL{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper,Map<String, String> joinModeMap) {
            wrapper.fullJoin(StrUtil.format("{tableName} {alias} ON {thisTableName}.{thisColumn} = {alias}.{thisColumn}",joinModeMap));
        }
    },
    ;

    public <T> void build(Class<T> entityType,MPJQueryWrapper<T> wrapper, Join join){
        Map<String, String> joinModeMap = new HashMap<>();
        joinModeMap.put("tableName", SqlHelper.table(join.entity()).getTableName());
        joinModeMap.put("alias", CharSequenceUtil.isEmpty(join.alias())?join.entity().getSimpleName():join.alias());
        joinModeMap.put("thisTableName",wrapper.getAlias());
        joinModeMap.put("thisColumn", ColumnTranslator.translatePropertyToColumn(entityType,join.joinField()));
        joinModeMap.put("joinColumn",ColumnTranslator.translatePropertyToColumn(join.entity(),join.thisField()));
        this.execute(wrapper,joinModeMap);
    }
    protected abstract <T> void execute(MPJQueryWrapper<T> wrapper,Map<String,String> joinModeMap);

}
