package com.nuc.zp.sourcecode.ioc;

public class Factorys {

    public static Agency getAgency(String name) {
        Agency agency = null;
        switch (name) {
            case "链家":
                agency = new LianjiaFactory().getAgency();
                break;
            case "自如":
                agency = new ZiruFactory().getAgency();
                break;
            default:
                throw new RuntimeException("无此中介");
        }
        return agency;
    }
}
