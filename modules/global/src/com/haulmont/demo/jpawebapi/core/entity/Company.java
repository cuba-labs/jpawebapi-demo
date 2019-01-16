/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */
package com.haulmont.demo.jpawebapi.core.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Table(name = "REF_COMPANY")
@Entity(name = "ref$Company")
public class Company extends Customer {
    private static final long serialVersionUID = -6508504579004882343L;

    @Column(name = "VAT_NUMBER")
    @Size(min = 3)
    @Pattern(regexp = "[A-Z]{2}.+")
    protected String vatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CEO_ID")
    private Manager ceo;

    public Manager getCeo() {
        return ceo;
    }

    public void setCeo(Manager ceo) {
        this.ceo = ceo;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getVatNumber() {
        return vatNumber;
    }
}