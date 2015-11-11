package com.omizil.x.purifier.biz.vo.enums;

/**
 * @author omizil on 15/10/31.
 */
public enum Group {

    Generic("generic", "一般敏感词"),
    Serious("serious", "严重敏感词"),
    ;

    private String code;
    private String desc;

    Group(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }

    public static Group getByCode(String code) {
        for (Group group : Group.values()) {
            if (group.code.equals(code)) {
                return group;
            }
        }
        return null;
    }
}
