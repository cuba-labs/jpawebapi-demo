/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */
package com.haulmont.demo.jpawebapi.core.entity.postcont;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;

import javax.annotation.PostConstruct;

@NamePattern("%s %s %s|firstName,middleName,lastName")
@MetaClass(name = "ref$ExtChildEntity")
public class ExtChildEntity extends ChildEntity implements HasMiddleName, HasAge {

    private static final long serialVersionUID = 4066357482307715461L;

    @MetaProperty
    protected String middleName;

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @PostConstruct
    protected void init() {
        setMiddleName("1");
    }

}