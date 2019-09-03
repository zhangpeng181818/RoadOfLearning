package com.nuc.zp.sourcecode.ioc;

/**
 * 房源
 */
public class House {
    /**
     * 小区名
     */
    private String name;
    /**
     * 地理位置
     */
    private String location;

    public House(){}

    public House(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
