package com.petros.springclient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;


@ApiModel(description = "Details about the animal")
public class Animal {

    @Id
    @GeneratedValue
    public Long id;

    @ApiModelProperty(notes = "The animal's common name.")
    public String name;

    @ApiModelProperty(notes = "The animal's most common color.", allowEmptyValue = true)
    @Nullable
    public String color;

    @ApiModelProperty(notes = "The family in which the animal belongs.", allowEmptyValue = true)
    @Nullable
    public String family;

    //TODO: try deleting getters and setters

    public Animal() {
    }

    public Animal(String name, String color, String family) {
        this.name = name;
        this.color = color;
        this.family = family;
    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public String getFamily() {
//        return family;
//    }

}
