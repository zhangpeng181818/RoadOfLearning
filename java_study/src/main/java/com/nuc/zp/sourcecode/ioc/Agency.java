package com.nuc.zp.sourcecode.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介公司
 */
public class Agency {
    /**
     * 中介公司名称
     */
    private String name;
    /**
     * 手里的房源
     */
    private List<House> houseList = new ArrayList<>();

    public Agency(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<House> getHouseList() {
        return houseList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHouseList(List<House> houseList) {
        this.houseList = houseList;
    }

    public House rentHouse(){
        return new House("空军大院","西三旗");
    }
}
