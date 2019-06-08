package com.nuc.zp.juc;

public enum SeasonEnum {
    SPRING(1,"春天","1"),
    SUMMER(2,"夏天","2"),
    FALL(3,"秋天","3"),
    WINTER(4,"冬天","4");

    private int retCode;
    private String retMessage;
    private String retMessage2;

    public static SeasonEnum getByCode(int retCode){
        SeasonEnum[] seasonEnums = SeasonEnum.values();
        for (SeasonEnum element: seasonEnums) {
            if (element.retCode == retCode){
                return element;
            }
        }
        return null;
    }


    SeasonEnum(int retCode, String retMessage, String retMessage2) {
        this.retCode = retCode;
        this.retMessage = retMessage;
        this.retMessage2 = retMessage2;
    }

    public int getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public String getRetMessage2() {
        return retMessage2;
    }
}
