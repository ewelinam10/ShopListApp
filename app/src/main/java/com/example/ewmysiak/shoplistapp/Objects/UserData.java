package com.example.ewmysiak.shoplistapp.Objects;

public class UserData {
    private int shopListCount;
    private String name;
    private String uuid;

    public UserData(int shopListCount, String name, String uuid) {
        this.shopListCount = shopListCount;
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public int getShopListCount() {
        return shopListCount;
    }
}
