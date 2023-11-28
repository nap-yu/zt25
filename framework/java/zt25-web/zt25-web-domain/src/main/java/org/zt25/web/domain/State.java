package org.zt25.web.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.zt25.core.ZObject;

import java.io.Serial;

/**
 * 请求描述对象
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
public class State implements ZObject {

    @Serial
    private static final long serialVersionUID = 6330564209792594617L;

    /**
     * 请求唯一id
     */
    private String requestId;

    /**
     * 请求时间戳
     */
    private Long timestamp;

    /**
     * 请求上下文(json字符串)
     */
    private String requestContext;

    /**
     * 请求上下文(json字符串)
     */
    private String target;

}
