package com.nuc.zp.quertz2.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class TestTask {

    public void test(String[] params) {
        log.info("我是带参数的test方法，正在被执行，参数为：{}" , Arrays.asList(params));
    }
    public void test1() {
        log.info("我是不带参数的test1方法，正在被执行");
    }

}
