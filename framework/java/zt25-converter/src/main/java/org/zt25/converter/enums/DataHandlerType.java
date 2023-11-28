package org.zt25.converter.enums;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.DesensitizedUtil;
import org.zt25.converter.annotation.ZDataHandler;
import org.zt25.converter.plugin.ZDataHandlerPlugin;

/**
 * 定义数据处理类型
 *
 * <p>
 *
 * </p>
 *
 * @author zhaotaiyu
 * @version 1.0
 * @since 2023/9/20 9:58
 **/
public enum DataHandlerType implements ZDataHandlerPlugin {

    // 不做处理
    NO_MASK {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) Convert.toStr(value);
        }
    },
    // 地址
    ADDRESS {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.address(Convert.toStr(value),annotation.sensitiveSize());
        }
    },
    // 银行卡号
    BANK_CARD {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.bankCard(Convert.toStr(value));
        }
    },
    // 中文姓名
    CHINESE_NAME {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.chineseName(Convert.toStr(value));
        }
    },
    // 邮件地址
    EMAIL {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.email(Convert.toStr(value));
        }
    },
    // 首字符
    FIRST {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.firstMask(Convert.toStr(value));
        }
    },
    // 证件号
    ID_CARD {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.idCardNum(Convert.toStr(value),annotation.front(),annotation.end());
        }
    },
    // ip地址(v4)
    IPV4 {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.ipv4(Convert.toStr(value));
        }
    },
    // IP地址(v6)
    IPV6 {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.ipv6(Convert.toStr(value));
        }
    },
    // 手机号
    MOBILE {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.mobilePhone(Convert.toStr(value));
        }
    },
    // 电话号码
    PHONE {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.fixedPhone(Convert.toStr(value));
        }
    },
    // 密码
    PASSWORD {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) DesensitizedUtil.password(Convert.toStr(value));
        }
    },
    // 自定义
    CUSTOM {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return null;
        }
    },
    // base64加密
    BASE64 {
        @Override
        protected <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation) {
            return (T) Base64.encode(Convert.toStr(value));
        }
    }
    ;

    @Override
    public <T> T invoke(Object source, Object name, Object value, T type, ZDataHandler annotation){
        return handler(source,name,value,type,annotation);
    }

    protected abstract <T> T handler(Object source, Object name, Object value, T type, ZDataHandler annotation);
}
