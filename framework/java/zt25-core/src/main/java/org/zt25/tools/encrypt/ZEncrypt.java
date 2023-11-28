package org.zt25.tools.encrypt;

/**
 * 加解密接口
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
public interface ZEncrypt {

    /**
     * 解密(默认base64)
     * @param str
     * @return
     */
    String decrypt(String str);

    /**
     * 加密(默认base64)
     * @param str
     * @return
     */
    String encrypt(String str);
}
