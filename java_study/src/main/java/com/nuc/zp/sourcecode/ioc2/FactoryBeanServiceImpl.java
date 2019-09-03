package com.nuc.zp.sourcecode.ioc2;

import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanServiceImpl implements FactoryBeanService {

    @Override
    public void testFactoryBean() {
        System.out.println("test FactoryBean");
    }

    @Test
    public void test() {
        try {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("test.xml");
        FactoryBeanService beanService = ctx.getBean(FactoryBeanService.class);
        beanService.testFactoryBean();
        System.out.println(beanService);

//        FactoryBeanStudy factoryBeanStudy = ctx.getBean(FactoryBeanStudy.class);
//        System.out.println(factoryBeanStudy.getObject());

//        System.out.println(factoryBeanStudy instanceof FactoryBean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}