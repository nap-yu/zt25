package org.zt25.persistence.orm.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.zt25.persistence.orm.injector.OrmSqlInjector;
import org.zt25.persistence.orm.interceptor.OrmParametersInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * mybatis-plus配置类
 *
 * <p>
 * 自定义mybatis-plus配置项
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Slf4j
@Component
@EnableConfigurationProperties(OrmProperties.class)
@RequiredArgsConstructor
public class MybatisPlusConfig {

    private final OrmProperties ormProperties;

    /**
     * 数据源类型
     */
    @Value("${spring.datasource.database:MARIADB}")
    private DbType dbType;

    @Primary
    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate oracleSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        // 添加公共参数处理拦截器
        sqlSessionFactory.getConfiguration().addInterceptor(new OrmParametersInterceptor(ormProperties));
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(dbType));// 分页插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());// 防止全表更新
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());// 乐观锁
        return interceptor;
    }

    @Bean
    public OrmSqlInjector sqlInjector() {
        return new OrmSqlInjector();
    }

}
