package com.nuc.zp.spring_cache.test;

import com.nuc.zp.spring_cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Testemp {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void getEmpById(){
        System.out.println(employeeMapper.getEmpById(1));
    }
}
