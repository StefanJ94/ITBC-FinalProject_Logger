package com.example.ITBC_Logger_Endpoints.enums;

public enum userType {

    CLIENT(0),
    ADMIN(1);


    public final int value;

    private userType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
