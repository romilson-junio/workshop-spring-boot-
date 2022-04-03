package com.romilson.workshopspringboot.domain.enums;

public enum TypeClient {
    PESSOA_FISICA(1,"Pessoa Física"),
    PESSOA_JURIDICA(2,"Pessoa Jurídica");
    
    private Integer code;
    private String description;

    private TypeClient(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TypeClient toEnum(Integer code){
        if(code == null) return null;
        for (TypeClient tc: TypeClient.values()) {
            if(code.equals(tc.getCode())) return tc;
        }
        throw new IllegalArgumentException("Id invalid: ".concat(String.valueOf(code)));
    }

}
