package com.gson.pojo;

import java.util.List;

public class NestedJSONList {
    private String name;
    private String last_name;
    private String repo;
    private List<Address> address;
    private List<OtherInfo> other_info;
    private List<Details> details;

    private class Address {
        private String street;
        private String house_number;
        private String Town;
    }

    private class OtherInfo {
        private String info;
    }

    private class Details {
    }

    public NestedJSONList(String name, String last_name, String repo, List<Address> address,
                          List<OtherInfo> other_info, List<Details> details) {
        this.name = name;
        this.last_name = last_name;
        this.repo = repo;
        this.address = address;
        this.other_info = other_info;
        this.details = details;
    }
}
