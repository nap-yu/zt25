package org.zt25.web.constants;

/**
 * @description
 * @author zhaotaiyu
 * @createDate 2023/3/3 9:58
 * @version 1.0
 **/
/**
 * web相关常量
 *
 * <p>
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public final class Constant {

    private Constant(){}

    /**
     * request相关默认值常量
     * LOCAL_IP: 本机ip
     * NULL_IP: 空ip
     * UNKNOWN_IP: 未知ip
     * IP_LENGTH: ip长度
     */
    public static final String LOCAL_IP = "127.0.0.1";
    public static final String NULL_IP = "0:0:0:0:0:0:0:1";
    public static final String UNKNOWN_IP = "UNKNOWN";
    public static final Integer IP_LENGTH = 15;

    /**
     * 分页默认值常量
     * MIN_PAGE_SIZE: 每页最小记录数
     * MAX_PAGE_SIZE: 每页最大记录数限制
     * DEFAULT_PAGE_SIZE: 默认分页记录数
     * DEFAULT_CURRENT_PAGE: 默认页码
     */
    public static final int MIN_PAGE_SIZE = 1;
    public static final int MAX_PAGE_SIZE = 10000;
    public static final long DEFAULT_PAGE_SIZE = 10L;
    public static final long DEFAULT_CURRENT_PAGE = 1L;
}
