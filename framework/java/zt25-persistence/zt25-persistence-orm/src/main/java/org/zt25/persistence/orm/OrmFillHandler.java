package org.zt25.persistence.orm;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

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
public interface OrmFillHandler {

    /**
     * 新增时填充数据
     * @param metaObjectHandler {@link MetaObjectHandler}
     * @param metaObject {@link MetaObject}
     */
    void insertFill(MetaObjectHandler metaObjectHandler, MetaObject metaObject);

    /**
     * 修改时填充数据
     * @param metaObjectHandler {@link MetaObjectHandler}
     * @param metaObject {@link MetaObject}
     */
    void updateFill(MetaObjectHandler metaObjectHandler, MetaObject metaObject);
}
