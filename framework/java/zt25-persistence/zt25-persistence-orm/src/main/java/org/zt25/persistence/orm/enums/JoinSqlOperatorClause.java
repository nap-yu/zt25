package org.zt25.persistence.orm.enums;

import cn.hutool.core.util.StrUtil;
import com.github.yulichang.query.MPJQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.zt25.persistence.annotation.Condition;
import org.zt25.persistence.domain.CO;
import org.zt25.persistence.enums.Connector;
import org.zt25.persistence.orm.ColumnTranslator;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
public enum JoinSqlOperatorClause {
    EQ {
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper,boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().eq(column,value);
            }else{
                wrapper.eq(column,value);
            }
        }
    },
    NE{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().ne(column,value);
            }else{
                wrapper.ne(column,value);
            }
        }
    },
    GT{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().gt(column,value);
            }else{
                wrapper.gt(column,value);
            }
        }
    },
    GE{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().ge(column,value);
            }else{
                wrapper.ge(column,value);
            }
        }
    },
    LT{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().lt(column,value);
            }else{
                wrapper.lt(column,value);
            }
        }
    },
    LE{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().le(column,value);
            }else{
                wrapper.le(column,value);
            }
        }
    },
    BETWEEN{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            List<Object> list = (List<Object>) value;
            if(isOr){
                wrapper.or().between(column,list.get(0),list.get(1));
            }else{
                wrapper.between(column,list.get(0),list.get(1));
            }
        }
    },
    NOT_BETWEEN{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            List<Object> list = (List<Object>) value;
            if(isOr){
                wrapper.or().notBetween(column,list.get(0),list.get(1));
            }else{
                wrapper.notBetween(column,list.get(0),list.get(1));
            }
        }
    },
    LIKE{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().like(column,value);
            }else{
                wrapper.like(column,value);
            }
        }
    },
    LIKE_LEFT{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().likeLeft(column,value);
            }else{
                wrapper.likeLeft(column,value);
            }
        }
    },
    LIKE_RIGHT{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().likeRight(column,value);
            }else{
                wrapper.likeRight(column,value);
            }
        }
    },
    NOT_LIKE{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().notLike(column,value);
            }else{
                wrapper.notLike(column,value);
            }
        }
    },
    NOT_LIKE_LEFT{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().notLikeLeft(column,value);
            }else{
                wrapper.notLikeLeft(column,value);
            }
        }
    },
    NOT_LIKE_RIGHT{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().notLikeRight(column,value);
            }else{
                wrapper.notLikeRight(column,value);
            }
        }
    },
    NULL{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().isNull(column);
            }else{
                wrapper.isNull(column);
            }
        }
    },
    NOT_NULL{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            if(isOr){
                wrapper.or().isNotNull(column);
            }else{
                wrapper.isNotNull(column);
            }
        }
    },
    IN{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            List<String> list = (List<String>) value;
            if(isOr){
                wrapper.or().in(column,list.toArray());
            }else{
                wrapper.in(column,list.toArray());
            }
        }
    },
    NOT_IN{
        @Override
        protected <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value) {
            List<String> list = (List<String>) value;
            if(isOr){
                wrapper.or().notIn(column,list.toArray());
            }else{
                wrapper.notIn(column,list.toArray());
            }
        }
    },
    ;

    protected abstract <T> void execute(MPJQueryWrapper<T> wrapper, boolean isOr, String column,Object value);

    public <T> void build(MPJQueryWrapper<T> wrapper, Class<?> entityType, CO co, Field field,String alias) {
        Condition cond = field.getAnnotation(Condition.class);
        String column = ColumnTranslator.translatePropertyToColumn(entityType, StrUtil.isEmpty(cond.field())?field.getName():cond.field());
        try {
            this.execute(wrapper, cond.connector().equals(Connector.OR),alias+"."+column,field.get(co));
        } catch (IllegalAccessException e) {
            log.error("解析条件对象属性异常:", e);
        }

    }
}
