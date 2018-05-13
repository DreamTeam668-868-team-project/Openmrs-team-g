/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.BaseOpenmrsObject;

/**
 * @author guanming
 */
public class ChartData extends BaseOpenmrsObject implements Serializable {

    private Integer id;

    private Date start;

    private Date end;

    private Integer number;

    private Integer userId;

    private String userGiven;

    private String userFamily;

    public ChartData(Date s, Date e, Integer n) {
        start = s;
        end = e;
        number = n;
    }

    public ChartData(Integer userId, String userGiven, String userFamily,
            Date s, Date e, Integer n) {
        this.userId = userId;
        this.userGiven = userGiven;
        this.userFamily = userFamily;
        start = s;
        end = e;
        number = n;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return start;
    }

    public void setTime(Date time) {
        this.start = time;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserGiven() {
        return userGiven;
    }

    public void setUserGiven(String userGiven) {
        this.userGiven = userGiven;
    }

    public String getUserFamily() {
        return userFamily;
    }

    public void setUserFamily(String userFamily) {
        this.userFamily = userFamily;
    }
}