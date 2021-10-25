package com.chainhaus.mtg.model;

/**
 * Created by Asad Sarwar on 25/06/2020.
 */
public class SelectModel {
    private Long id;
    private String value;

    public SelectModel(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
