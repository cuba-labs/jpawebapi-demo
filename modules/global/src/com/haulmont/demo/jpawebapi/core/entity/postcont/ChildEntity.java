/*
 * Copyright (c) 2008-2018 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */
package com.haulmont.demo.jpawebapi.core.entity.postcont;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;

import javax.annotation.PostConstruct;

@NamePattern("%s %s|firstName,lastName")
@MetaClass(name = "ref$ChildEntity")
public class ChildEntity extends BaseEntity implements HasLastName {
    private static final long serialVersionUID = 4463248134134367357L;

    @MetaProperty
    protected Integer age;

    @MetaProperty
    protected String lastName;

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @PostConstruct
    protected void init() {
        setLastName("1");
    }

    @PostConstruct
    public void initAge() {
        setAge(1);
    }
}