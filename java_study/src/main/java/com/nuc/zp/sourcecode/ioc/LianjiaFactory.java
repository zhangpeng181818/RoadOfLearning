package com.nuc.zp.sourcecode.ioc;

/**
 * 链家平台
 */
public class LianjiaFactory implements AgencyFactory {
    @Override
    public Agency getAgency() {
        return new Agency("链家中介");
    }

}
