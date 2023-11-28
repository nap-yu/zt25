package org.zt25.persistence.orm;

import com.github.yulichang.base.MPJBaseMapper;

import java.util.List;

/**
 * ORM mapper接口
 *
 * <p>
 * 添加了批量新增和批量保存两个接口
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public interface OrmMapper<E> extends MPJBaseMapper<E> {

    /**
     * 批量插入
     * @param entities 数据集合
     * @return 插入数据数
     */
    int insertBatchSomeColumn(List<E> entities);

    int upsert(List<E> entities);

}
