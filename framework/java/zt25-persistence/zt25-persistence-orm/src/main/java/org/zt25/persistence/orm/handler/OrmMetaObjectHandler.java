package org.zt25.persistence.orm.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.zt25.persistence.orm.OrmFillHandler;
import org.zt25.persistence.orm.annotation.ZFillHandler;
import org.zt25.tools.SpringUtil;
import org.springframework.stereotype.Component;

/**
 * 自定义数据填充接口
 *
 * <p>
 * 需实现
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Slf4j
@Component
public class OrmMetaObjectHandler implements MetaObjectHandler {

    /**
     * 开启新增填充字段
     */
    @Override
    public boolean openInsertFill() {
        return MetaObjectHandler.super.openInsertFill();
    }

    /**
     * 开启更新填充字段
     */
    @Override
    public boolean openUpdateFill() {
        return MetaObjectHandler.super.openUpdateFill();
    }

    @Override
    public MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject) {

        return MetaObjectHandler.super.setFieldValByName(fieldName, fieldVal, metaObject);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        ZFillHandler fillHandler = metaObject.getOriginalObject().getClass().getAnnotation(ZFillHandler.class);

        if(ObjectUtil.isNull(fillHandler)){
            this.getDefaultFillHandler().insertFill(this,metaObject);
        }else {
            if(fillHandler.skipDefaultFill()){
                this.getCustomizeFillHandler(fillHandler).insertFill(this,metaObject);
            }else{
                this.getDefaultFillHandler().insertFill(this,metaObject);
                this.getCustomizeFillHandler(fillHandler).insertFill(this,metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        ZFillHandler fillHandler = metaObject.getOriginalObject().getClass().getAnnotation(ZFillHandler.class);

        if(ObjectUtil.isNull(fillHandler)){
            this.getDefaultFillHandler().updateFill(this,metaObject);
        }else {

            if(fillHandler.skipDefaultFill()){
                this.getCustomizeFillHandler(fillHandler).updateFill(this,metaObject);
            }else{
                this.getDefaultFillHandler().updateFill(this,metaObject);
                this.getCustomizeFillHandler(fillHandler).updateFill(this,metaObject);
            }
        }
    }

    /**
     * 获取自定义CustomizeFillHandler
     * @return {@link OrmFillHandler}
     */
    private OrmFillHandler getDefaultFillHandler(){
        return SpringUtil.getBean(DefaultFillHandler.class);
    }

    /**
     * 获取自定义CustomizeFillHandler
     * @return {@link OrmFillHandler}
     */
    private OrmFillHandler getCustomizeFillHandler(ZFillHandler fillHandler){
        return SpringUtil.getBean(fillHandler.fill());
    }


}
