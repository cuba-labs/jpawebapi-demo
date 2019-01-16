/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */
package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;

@MappedSuperclass
@MetaClass(name = "ref$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = 1776347877619289241L;

    @Column(name = "NAME")
    protected String name;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_GROUP_ID")
    protected CustomerGroup customerGroup;

    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_GROUP2_ID")
    protected CustomerGroup customerGroup2;

    @OnDeleteInverse(DeletePolicy.UNLINK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_GROUP3_ID")
    protected CustomerGroup customerGroup3;

    public void setCustomerGroup(CustomerGroup customerGroup) {
        this.customerGroup = customerGroup;
    }

    public CustomerGroup getCustomerGroup() {
        return customerGroup;
    }

    public CustomerGroup getCustomerGroup2() {
        return customerGroup2;
    }

    public void setCustomerGroup2(CustomerGroup customerGroup2) {
        this.customerGroup2 = customerGroup2;
    }

    public CustomerGroup getCustomerGroup3() {
        return customerGroup3;
    }

    public void setCustomerGroup3(CustomerGroup customerGroup3) {
        this.customerGroup3 = customerGroup3;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}