package com.nuc.zp.sourcecode.ioc;

/**
 * 北漂男人
 */
public class BeiPiaoMan {

    public static void main(String[] args) {
        /**
         * 传统方式租房：
         * 到各大小区公告栏，查看是否有房屋出租，找到出租的公告，给房东打电话，看房，签合同，直接和房东交流。
         */
        House house = new House("天通苑小区", "昌平区五环外");
        System.out.println(house);

        /**
         * 现在互联网租房：
         * 直接找中介，中介带你看房，想要看哪里的房子和中介交流，不用在各个小区爬公告栏了。
         */
        House agencyHouse = Factorys.getAgency("自如").rentHouse();
        System.out.println(agencyHouse);

    }

}
