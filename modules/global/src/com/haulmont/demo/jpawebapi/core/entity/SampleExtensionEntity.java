/*
 * Copyright (c) 2008-2016 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.haulmont.demo.jpawebapi.core.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "ref$SampleExtensionEntity")
@Extends(SampleProductEntity.class)
public class SampleExtensionEntity extends SampleProductEntity {

    @Column(name = "INFO")
    protected String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
