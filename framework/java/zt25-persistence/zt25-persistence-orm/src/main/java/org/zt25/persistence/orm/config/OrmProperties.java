package org.zt25.persistence.orm.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.zt25.persistence.enums.PkType;

@Component
@ConfigurationProperties("zt25.orm")
@Data
public class OrmProperties {

    /**
     * 全局默认主键类型
     */
    @Value("${zt25.orm.default-pk-type:DEFAULT}")
    private PkType pkType;
}
