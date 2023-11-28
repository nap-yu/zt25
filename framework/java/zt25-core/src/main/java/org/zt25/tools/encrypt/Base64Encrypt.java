package org.zt25.tools.encrypt;

import cn.hutool.core.codec.Base64;
import org.springframework.stereotype.Component;

/**
 * base64 加密实现
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @since 2023/3/3 9:58
 * @version 1.0
 **/
@Component("base64Encrypt")
public class Base64Encrypt implements ZEncrypt {

    @Override
    public String decrypt(String body) {
        //Base64.Decoder decoder = Base64.getDecoder();
        //byte[] decrypt = decoder.decode(body);
        //return new String(decrypt, StandardCharsets.UTF_8);
        return Base64.decodeStr(body);
    }

    @Override
    public String encrypt(String body) {
        //Base64.Encoder encoder = Base64.getEncoder();
        //return encoder.encodeToString(JSON.toJSONString(body).getBytes(StandardCharsets.UTF_8));
        return Base64.encode(body);
    }
}
