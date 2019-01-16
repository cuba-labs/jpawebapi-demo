/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity.sample;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.demo.jpawebapi.core.entity.Colour;
import com.haulmont.demo.jpawebapi.core.entity.DriverStatus;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@MetaClass(name = "ref$TestEntity")
public class TestEntity extends BaseUuidEntity {

    private static final long serialVersionUID = -7276371762923303054L;

    @MetaProperty
    protected String name;

    @MetaProperty
    protected Boolean valid;

    @MetaProperty
    protected Date date;

    @MetaProperty
    protected LocalDateTime localDateTime;

    @MetaProperty
    private String unit;

    @MetaProperty
    private Integer count;

    @MetaProperty
    private List<Unit> units;

    @MetaProperty
    protected User user;

    @MetaProperty
    protected Colour colour;

    @MetaProperty
    private List<Colour> colours;

    @MetaProperty
    private Integer status;

    public List<Colour> getColours() {
        return colours;
    }

    public void setColours(List<Colour> colours) {
        this.colours = colours;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Unit getUnit() {
        return Unit.fromId(unit);
    }

    public void setUnit(Unit unit) {
        this.unit = unit == null ? null: unit.getId();
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public DriverStatus getStatus() {
        return status == null ? null : DriverStatus.fromId(status);
    }

    public void setStatus(DriverStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public Boolean getValid(){
        return valid;
    }

    public void setValid(Boolean valid){
        this.valid = valid;
    }
}
