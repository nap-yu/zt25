package org.zt25.web.domain;

/**
 * 应用上下文对象
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/3/3 9:58
 **/
public final class ZContext {

    private ZContext() {
    }


    /**
     * 保存上下文对象的ThreadLocal
     */
    private static final ThreadLocal<State> USER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 添加当前登录用户上下文
     *
     * @param state {@link State}
     */
    public static void addState(State state) {
        USER_THREAD_LOCAL.set(state);
    }

    /**
     * 获取请求描述对象
     *
     * @return {@link State}
     */
    public static State getState() {
        return USER_THREAD_LOCAL.get();
    }

    /**
     * 删除当前上下文.防止内存泄漏
     */
    public static void remove() {
        USER_THREAD_LOCAL.remove();
    }

}
