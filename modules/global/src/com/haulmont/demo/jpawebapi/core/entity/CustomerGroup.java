///*
// * Copyright (c) 2008-2016 Haulmont. All rights reserved.
// * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
// */
//package com.haulmont.demo.jpawebapi.core.entity;
//
//import com.haulmont.chile.core.annotations.NamePattern;
//import com.haulmont.cuba.core.entity.StandardEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//@NamePattern("%s|name")
//@Table(name = "REF_CUSTOMER_GROUP")
//@Entity(name = "ref$CustomerGroup")
//public class CustomerGroup extends StandardEntity {
//    private static final long serialVersionUID = -4585729037114703565L;
//
//    @Column(name = "NAME")
//    protected String name;
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//
//}