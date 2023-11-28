package org.zt25.persistence.orm.injector;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.Upsert;
import com.github.yulichang.injector.MPJSqlInjector;

import java.util.List;

/**
 * 自定义拦截器
 *
 * <p>
 * 批量添加及批量保存的处理
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public class OrmSqlInjector extends MPJSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methods = super.getMethodList(mapperClass,tableInfo);
        methods.add(new InsertBatchSomeColumn(i->i.getFieldFill()!= FieldFill.UPDATE));
        methods.add(new Upsert());
        return methods;
    }
}
