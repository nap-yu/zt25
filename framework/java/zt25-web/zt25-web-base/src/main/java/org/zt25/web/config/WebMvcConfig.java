package org.zt25.web.config;


import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ClassUtil;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zt25.web.WebException;
import org.zt25.web.annotation.CustomInterceptor;
import org.zt25.web.enums.WebError;
import org.zt25.web.interceptor.RequestInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * MVC配置类
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 是否开启默认拦截器
     */
    @Value("${zt25.web.interceptor.default.enable:true}")
    protected boolean enable;

    /**
     * 默认拦截器的拦截路径
     */
    @Value("${zt25.web.interceptor.default.addPath:/**}")
    protected String addPath;

    /**
     * 默认拦截器的排除路径
     */
    @Value("${zt25.web.interceptor.default.excludePath:}")
    protected String excludePath;

    /**
     * 默认拦截器的创建顺序
     */
    @Value("${zt25.web.interceptor.default.order:100}")
    protected Integer order;

    /**
     * 自定义拦截器的包路径
     */
    @Value("${zt25.web.interceptor.custom.packagePath:}")
    protected String packagePath;


    /**
     * 自定义拦截器
     * @param registry {@link InterceptorRegistry}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置自定义拦截器
        List<? extends HandlerInterceptor> interceptors = this.scanCustomInterceptor();
        this.addCustomInterceptor(registry,interceptors);

        if(this.enable){
            registry.addInterceptor(new RequestInterceptor())
                    .addPathPatterns(this.addPath)
                    .excludePathPatterns("/error",CharSequenceUtil.isEmpty(this.excludePath)|| CharSequenceUtil.equalsIgnoreCase("null",this.excludePath) ?"":this.excludePath)
                    .order(this.order);
        }
    }

    /**
     * 与org.springframework.web.servlet.config.annotation.WebMvcConfigurer.configureMessageConverters()方法的区别是：
     *  使用configureMessageConverters方法会导致springboot不会注入默认的消息转换器
     *
     * @param converters {@link HttpMessageConverter}
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
        config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(0, converter);
    }


    /**
     * 交换MappingJackson2HttpMessageConverter与第一位元素
     * 让返回值类型为String的接口能正常返回包装结果
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (int i = 0; i < converters.size(); i++) {
            if (converters.get(i) instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter) converters.get(i);
                converters.set(i, converters.get(0));
                converters.set(0, mappingJackson2HttpMessageConverter);
                break;
            }
        }
    }

    /**
     * 扫描自定义拦截器
     * @return 自定义拦截对象集合
     */
    private <T extends HandlerInterceptor> List<T> scanCustomInterceptor(){
        List<T> interceptors = new ArrayList<>();
        if(CharSequenceUtil.isNotBlank(this.packagePath)){
            // 扫描自定包路径下的自定义拦截器类
            Set<Class<?>> set = ClassUtil.scanPackageByAnnotation(this.packagePath, CustomInterceptor.class);
            for (Class<?> c : set) {
                try {
                    interceptors.add((T) this.createInstance(c));
                } catch (Exception e) {
                    log.warn("拦截器:" + c.getName() + "创建失败 [原因:" + e.getMessage() + "]");
                }
            }
        }

        return interceptors;
    }

    /**
     * 添加自定义拦截器
     * @param registry {@link InterceptorRegistry}
     * @param interceptors 自定义拦截对象集合
     */
    private <T  extends HandlerInterceptor> void addCustomInterceptor(InterceptorRegistry registry, List<T> interceptors){
        for(T i:interceptors){
            CustomInterceptor customInterceptor = i.getClass().getAnnotation(CustomInterceptor.class);
            registry.addInterceptor(i)
                    .addPathPatterns(customInterceptor.addPath())
                    .excludePathPatterns(customInterceptor.excludePath())
                    .order(customInterceptor.order());
        }
    }

    /**
     * 创建自定义拦截器实例
     * @param checkType 自定义拦截器类型
     * @return 自定义拦截器实例
     */
    private <T> T createInstance(Class<T> checkType) throws Exception {
        T obj = checkType.getDeclaredConstructor().newInstance();
        //需要检查checkType是不是obj的字节码对象
        if (!checkType.isInstance(obj)) {
            throw new WebException(WebError.CREATE_CUSTOM_INTERCEPTOR_INSTANCE_EXCEPTION,"对象跟字节码不兼容");
        }

        return obj;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 配置自定义接收参数
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new JsonObjectArgResolverHandler());
    }
}
