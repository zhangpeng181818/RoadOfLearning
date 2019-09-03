package com.nuc.zp.sourcecode.ioc;

/**
 * 自如平台
 */
public class ZiruFactory implements AgencyFactory {
    @Override
    public Agency getAgency() {
        return new Agency("自如中介");
    }
}
