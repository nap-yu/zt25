package org.zt25.converter.plugin.mask;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.DesensitizedUtil;
import org.zt25.converter.annotation.ZDataHandler;
import org.zt25.converter.plugin.ZDataHandlerPlugin;

public class ChineseNameMaskHandler implements ZDataHandlerPlugin {

    @Override
    public <T> T invoke(Object source, Object name, Object value, T type, ZDataHandler annotation) {
        return (T) DesensitizedUtil.chineseName(Convert.toStr("赵太宇"));
    }
}
