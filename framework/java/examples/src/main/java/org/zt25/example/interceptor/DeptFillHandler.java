package org.zt25.example.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.zt25.persistence.orm.OrmFillHandler;
import org.springframework.stereotype.Component;

@Component
public class DeptFillHandler implements OrmFillHandler {
    @Override
    public void insertFill(MetaObjectHandler metaObjectHandler, MetaObject metaObject) {
        System.out.println("insertFill");
    }

    @Override
    public void updateFill(MetaObjectHandler metaObjectHandler, MetaObject metaObject) {
        System.out.println("updateFill");
    }
}
