///*
// * Copyright (c) 2008-2016 Haulmont. All rights reserved.
// * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
// */
//package com.haulmont.demo.jpawebapi.core.entity;
//
//import com.haulmont.chile.core.annotations.NamePattern;
//
//import javax.persistence.*;
//
//@Entity(name = "ref$SampleCard")
//@Table(name = "REF_SAMPLE_CARD")
//@DiscriminatorValue("300")
//@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
//@NamePattern("%s|description")
//public class SampleCard extends Card {
//
//    private static final long serialVersionUID = -8498805050096372936L;
//
//    @Column(name = "INFO", length = 50)
//    protected String info = "";
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "SHELF_ID")
//    protected Shelf shelf;
//
//    public String getInfo() {
//        return info;
//    }
//
//    public void setInfo(String info) {
//        this.info = info;
//    }
//
//    public Shelf getShelf() {
//        return shelf;
//    }
//
//    public void setShelf(Shelf shelf) {
//        this.shelf = shelf;
//    }
//}
