package org.zt25.web.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zt25.core.ZObject;
import org.zt25.web.constants.Constant;

import java.io.Serial;

/**
 * 分页查询对象
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public sealed class PageQuery implements ZObject permits ZPage {

    @Serial
    private static final long serialVersionUID = -7783720588961815588L;

    /**
     * 构造器
     * @param current 当前页
     * @param size 页条数
     */
    public PageQuery(long current, long size) {
        this.current = current;
        this.size = size;
    }

    /**
     * 默认当前页
     */
    @Min(value = Constant.DEFAULT_CURRENT_PAGE, message = "页数应该大于等于1")
    private Long current = Constant.DEFAULT_CURRENT_PAGE;

    /**
     * 默认分页记录数
     */
    @Min(value = Constant.MIN_PAGE_SIZE, message = "每页数据的数量应该大于等于1")
    @Max(value = Constant.MAX_PAGE_SIZE, message = "每页数据的数量应该小于：" + Constant.MAX_PAGE_SIZE)
    private Long size = Constant.DEFAULT_PAGE_SIZE;
}
