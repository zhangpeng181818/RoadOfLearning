package com.nuc.zp.sourcecode.ioc2;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FactoryBeanStudy implements FactoryBean<FactoryBeanService> {
    @Override
    public FactoryBeanService getObject() throws Exception {
        return new FactoryBeanServiceImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
