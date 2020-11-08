package com.holidu.interview.assignment.integration.soda2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.ws.rs.core.GenericType;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Tree {
    public static final GenericType<List<Tree>> LIST_TYPE = new GenericType<>() {};
    private String name;
    private  Double x;
    private  Double y;

    public Tree() {
    }

    public Tree(String name, Double x, Double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @JsonProperty("spc_common")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("x_sp")
    public void setX(Double x) {
        this.x = x;
    }

    @JsonProperty("y_sp")
    public void setY(Double y) {
        this.y = y;
    }
}
