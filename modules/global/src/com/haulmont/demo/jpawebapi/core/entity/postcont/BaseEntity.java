/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */
package com.haulmont.demo.jpawebapi.core.entity.postcont;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import javax.annotation.PostConstruct;

@NamePattern("%s|firstName")
@MetaClass(name = "ref$BaseEntity")
public class BaseEntity extends BaseUuidEntity implements HasFirstName {
    private static final long serialVersionUID = 7576409658392474724L;

    @MetaProperty
    protected String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @PostConstruct
    protected void init() {
        setFirstName("1");
    }
}