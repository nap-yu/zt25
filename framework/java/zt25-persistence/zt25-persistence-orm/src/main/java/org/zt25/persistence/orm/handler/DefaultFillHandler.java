package org.zt25.persistence.orm.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.zt25.persistence.orm.OrmFillHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DefaultFillHandler implements OrmFillHandler {
    @Override
    public void insertFill(MetaObjectHandler metaObjectHandler, MetaObject metaObject) {
        metaObjectHandler.strictInsertFill(metaObject,"createTime", Date::new,Date.class);
    }

    @Override
    public void updateFill(MetaObjectHandler metaObjectHandler, MetaObject metaObject) {
        metaObjectHandler.strictInsertFill(metaObject,"updateTime", Date::new,Date.class);
    }
}
