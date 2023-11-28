package org.zt25.web.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ZDto<T extends Serializable>  implements Serializable {

    @Serial
    private static final long serialVersionUID = -1757927173843820019L;

    private T id;

}
