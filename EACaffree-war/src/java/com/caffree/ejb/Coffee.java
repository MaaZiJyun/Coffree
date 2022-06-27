/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.caffree.ejb;

/**
 *
 * @author 37409
 */
public class Coffee {
    
    protected int id;
    protected String u_name;
    protected String c_name;
    protected int sugar;
    protected int temperature;
    
    public Coffee(){
    }
    
    public Coffee(int id){
        this.id = id;
    }

    public Coffee(int id, String u_name, String c_name, int sugar, int temperature) {
        this.id = id;
        this.u_name = u_name;
        this.c_name = c_name;
        this.sugar = sugar;
        this.temperature = temperature;
    }

    public Coffee(String u_name, String c_name, int sugar, int temperature) {
        this.u_name = u_name;
        this.c_name = c_name;
        this.sugar = sugar;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public float getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    
}
