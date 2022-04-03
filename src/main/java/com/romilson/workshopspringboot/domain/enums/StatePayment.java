package com.romilson.workshopspringboot.domain.enums;

public enum StatePayment {
    PENDING(1, "Pending"),
    PAID_OFF(2, "Paid Off"),
    CANCELED(3, "Canceled");

    private Integer code;
    private String description;

    private StatePayment(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static StatePayment toEnum(Integer code){
        if(code == null) return null;
        for (StatePayment sp: StatePayment.values()) {
            if(code.equals(sp.getCode())) return sp;
        }
        throw new IllegalArgumentException("Id invalid: ".concat(String.valueOf(code)));
    }
}
