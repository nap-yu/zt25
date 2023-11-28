package org.zt25.converter.bean;

import lombok.Data;
import org.zt25.converter.annotation.ZDataHandler;
import org.zt25.converter.enums.DataHandlerType;

import java.util.Date;

@Data
public class UserCertificate<T> {

    private T id;

    @ZDataHandler(type = DataHandlerType.ID_CARD)
    private String no;

    private CertificateType certificateType;

    private Date effectiveStartDate;
    private Date effectiveEndDate;
    private Boolean status;
}