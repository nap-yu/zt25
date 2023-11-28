package org.zt25.web.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.zt25.core.ZObject;

import java.io.Serial;

/**
 * 请求对象
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Accessors(chain = true)
@Data
public class Request implements ZObject {

    @Serial
    private static final long serialVersionUID = 6796774279908679285L;

    /**
     * 分页查询对象 {@link PageQuery}
     */
    private PageQuery pageQuery;

    /**
     * 扩展类 {@link State}
     */
    private State state;
}
