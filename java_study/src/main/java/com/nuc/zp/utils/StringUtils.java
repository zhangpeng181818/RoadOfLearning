package com.nuc.zp.utils;

import cn.encmed.service.CascadedServer;

public class StringUtils {

    public static Integer byteToInteger(Byte b) {
        return 0xff & b;
    }

    public static void main(String[] args) {
        System.out.println(byteToInteger(Byte.MIN_VALUE));


        CascadedServer casecadServer = new CascadedServer();
       casecadServer.start();



    }
}
