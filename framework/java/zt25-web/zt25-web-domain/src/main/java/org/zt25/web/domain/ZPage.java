package org.zt25.web.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.List;

/**
 * 分页对象
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Data
public final class ZPage<T> extends PageQuery {

    @Serial
    private static final long serialVersionUID = -3015363695948402098L;

    /**
     * 构造器
     * @param current 当前页
     * @param size 页条数
     */
    public ZPage(long current, long size, long total) {
        super(current,size);
        this.total = total;
    }

    /**
     * 构造器
     * @param total 总数
     * @param pages 总页数
     */
    public ZPage(long total, long pages) {
        this.total = total;
        this.pages = pages;
    }

    /**
     * 总页数
     */
    private Long pages = 0L;

    /**
     * 总条数
     */
    private Long total = 0L;

    /**
     * 结果集
     */
    private List<T> data;
}
