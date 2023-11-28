package org.zt25.converter.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CertificateType {

    SFZ("0","身份证"),
    HZ("1","护照"),
    HKB("2","户口本"),
    JGZ("3","军官证");

    private final String code;
    private final String desc;

    //public static CertificateEnum getCertificate(String code) {
    //    for (CertificateEnum type : CertificateEnum.values()) {
    //        if (type.code.equalsIgnoreCase(code)) {
    //            return type;
    //        }
    //    }
    //    return SFZ;
    //}
}
