/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.Date;

/**
 *
 */
@MetaClass(name = "ref$SampleHierarchicalEntity")
@NamePattern("#getCaption|name")
public class SampleHierarchicalEntity extends BaseUuidEntity {

    private static final long serialVersionUID = 622360266800979387L;

    @MetaProperty
    protected SampleHierarchicalEntity parent;

    @MetaProperty
    protected String name;

    @MetaProperty
    protected Date date;

    @MetaProperty
    protected Boolean active;

    public SampleHierarchicalEntity getParent() {
        return parent;
    }

    public void setParent(SampleHierarchicalEntity parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCaption() {
        if (active) {
            return name + " is active";
        }

        return name + " isn't active";
    }
}
