///*
// * Copyright (c) 2008-2016 Haulmont. All rights reserved.
// * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
// */
//
//package com.haulmont.demo.jpawebapi.core.entity;
//
//import com.haulmont.cuba.core.entity.StandardEntity;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * For testing self referenced entity only
// *
// */
//@Table(name = "REF_MANAGER")
//@Entity(name = "ref$Manager")
//public class Manager extends StandardEntity {
//    private static final long serialVersionUID = 6516336688901709797L;
//
//    @OneToMany(mappedBy = "chief")
//    protected List<Manager> subordinates;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "CHIEF_ID")
//    protected Manager chief;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "COMPANY_ID")
//    protected Company company;
//
//    @Column(name = "NAME")
//    protected String name;
//
//    public void setChief(Manager chief) {
//        this.chief = chief;
//    }
//
//    public Manager getChief() {
//        return chief;
//    }
//
//    public void setSubordinates(List<Manager> subordinates) {
//        this.subordinates = subordinates;
//    }
//
//    public List<Manager> getSubordinates() {
//        return subordinates;
//    }
//
//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}