///*
// * Copyright (c) 2008-2016 Haulmont. All rights reserved.
// * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
// */
//
//package com.haulmont.demo.jpawebapi.core.entity;
//
//import com.haulmont.chile.core.annotations.NamePattern;
//import com.haulmont.cuba.core.entity.StandardEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import java.util.Date;
//
//@Entity(name = "ref$Point")
//@Table(name = "REF_POINT")
//@NamePattern("%s|yValue1")
//public class Point extends StandardEntity {
//    @Column(name = "X_VALUE")
//    private Date xValue;
//
//    @Column(name = "Y_VALUE_1")
//    private Double yValue1;
//
//    @Column(name = "Y_VALUE_2")
//    private Double yValue2;
//
//    public Date getXValue() {
//        return xValue;
//    }
//
//    public void setXValue(Date xValue) {
//        this.xValue = xValue;
//    }
//
//    public Double getYValue1() {
//        return yValue1;
//    }
//
//    public void setYValue1(Double yValue1) {
//        this.yValue1 = yValue1;
//    }
//
//    public Double getYValue2() {
//        return yValue2;
//    }
//
//    public void setYValue2(Double yValue2) {
//        this.yValue2 = yValue2;
//    }
//}