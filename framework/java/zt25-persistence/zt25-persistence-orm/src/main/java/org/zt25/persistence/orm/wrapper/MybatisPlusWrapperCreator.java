package org.zt25.persistence.orm.wrapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.zt25.persistence.StatementCreator;

/**
 * mybatis-plus查询构造器接口
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/9/22 19:58
 * @version 1.0
 **/
public interface MybatisPlusWrapperCreator<E, W extends Wrapper<E>> extends StatementCreator<E, W> {

}
